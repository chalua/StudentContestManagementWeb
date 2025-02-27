package com.datn.qlct.components;

import com.datn.qlct.entity.AdminEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.entity.SinhVienEntity;
import com.datn.qlct.exception.MyException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration; // save to an environment variable

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(SinhVienEntity user) throws Exception {
        // properties => claims
        Map<String, Object> claims = new HashMap<>();
        // this.generateSecretKey();
        claims.put("maSinhVien", user.getMaSinhVien());
        claims.put("hoTen", user.getHoTen());
        claims.put("email", user.getEmail());
        claims.put("soDienThoai", user.getSoDienThoai());
        try {
            String token = Jwts.builder()
                    .setClaims(claims) // how to extract claims from this ? Payload
                    .setSubject(claims.values().toString())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L)) // 30 ngày
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
            // you can "inject" Logger, instead System.out.println
            throw new MyException("Cannot create jwt token, error: " + e.getMessage());
            // return null;
        }
    }

    public String generateTokenGV(GiangVienEntity gv) throws Exception {
        // properties => claims
        Map<String, Object> claims = new HashMap<>();
        // this.generateSecretKey();
        claims.put("maGiangVien", gv.getMaGiangVien());
        claims.put("tenGiangVien", gv.getTenGiangVien());
        claims.put("gioiTinh", gv.getGioiTinh());
        claims.put("soDienThoai", gv.getSoDienThoai());
        claims.put("hocHam", gv.getMaHocHam() == null ? "null" : gv.getMaHocHam().getTenHocHam());
        claims.put("hocVi", gv.getMaHocVi() == null ? "null" : gv.getMaHocVi().getTenHocVi());
        claims.put("queQuan", gv.getQueQuan());
        try {
            String token = Jwts.builder()
                    .setClaims(claims) // how to extract claims from this ? Payload
                    .setSubject(claims.values().toString())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L)) // 30 ngày
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
            // you can "inject" Logger, instead System.out.println
            throw new MyException("Cannot create jwt token, error: " + e.getMessage());
            // return null;
        }
    }

    public String generateTokenAdmin(AdminEntity admin) throws Exception {
        // properties => claims
        Map<String, Object> claims = new HashMap<>();
        // this.generateSecretKey();
        claims.put("username", admin.getUserName());
        try {
            String token = Jwts.builder()
                    .setClaims(claims) // how to extract claims from this ? Payload
                    .setSubject(claims.values().toString())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L)) // 30 ngày
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
            // you can "inject" Logger, instead System.out.println
            throw new MyException("Cannot create jwt token, error: " + e.getMessage());
            // return null;
        }
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        // Keys.hmacShaKeyFor(Decoders.BASE64.decode("TaqlmGv1iEDMRiFp/pHuID1+T84IABfuA0xXh4GhiUI="));
        return Keys.hmacShaKeyFor(bytes);
    }

    // private String generateSecretKey() {
    // SecureRandom random = new SecureRandom();
    // byte[] keyBytes = new byte[32]; // 256-bit key
    // random.nextBytes(keyBytes);
    // String secretKey = Encoders.BASE64.encode(keyBytes);
    // return secretKey;
    // }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // check expiration
    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, claims -> claims.get("maSinhVien", String.class));
    }

    public String getUsernameGVFromToken(String token) {
        return extractClaim(token, claims -> claims.get("maGiangVien", String.class));
    }

    public String getUsernameAdminFromToken(String token) {
        return extractClaim(token, claims -> claims.get("username", String.class));
    }

    // public String extractPhoneNumber(String token) {
    // return extractClaim(token, Claims::getSubject);
    // }
    // public boolean validateToken(String token, UserDetails userDetails) {
    // String phoneNumber = extractPhoneNumber(token);
    // return (phoneNumber.equals(userDetails.getUsername()))
    // && !isTokenExpired(token); //check hạn của token
    // }
}
