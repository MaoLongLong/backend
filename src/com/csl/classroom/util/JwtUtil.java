package com.csl.classroom.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.csl.classroom.dto.LoginParam;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author MaoLongLong
 * @date 2020-12-28 12:48
 */
@Slf4j
public class JwtUtil {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Props PROPS;

    private static final String SECRET;
    private static final Long EXPIRATION;
    private static final String HEADER;
    private static final Set<String> WHITE_LIST;

    static {
        PROPS = new Props("jwt.properties");
        SECRET = PROPS.getStr("jwt.secret");
        EXPIRATION = PROPS.getLong("jwt.expiration");
        HEADER = PROPS.getStr("jwt.header") + " ";
        String skip = PROPS.getStr("jwt.skip");
        WHITE_LIST = CollUtil.newHashSet(StrUtil.split(skip, ','));
    }

    private JwtUtil() {
    }

    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String generateToken(LoginParam param) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", param.getUsername());
        claims.put("created", new Date());
        return generateToken(claims);
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败: {}", token);
        }
        return claims;
    }

    public static boolean checkToken(String token) {

        if (StrUtil.isBlank(token) || !token.startsWith(HEADER)) {
            return false;
        }

        Claims claims = getClaimsFromToken(StrUtil.removePrefix(token, HEADER));
        if (claims == null) {
            return false;
        }

        Date expiration = claims.getExpiration();
        return !expiration.before(new Date());
    }

    public static String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(StrUtil.removePrefix(token, HEADER));
            username = claims.getSubject();
        } catch (Exception e) {
            log.info("从JWT获取用户名失败: {}", token);
        }
        return username;
    }

    public static boolean skip(String uri) {
        return WHITE_LIST.contains(uri);
    }

    private static Date generateExpiration() {
        return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    }
}
