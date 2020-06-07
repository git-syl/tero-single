package com.okfolio.tero.common;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public class AuthorityPool {

    private final static ConcurrentMap<String, ConcurrentMap<String, Authority>> AUTHORITIES = Maps.newConcurrentMap();

    public static void put(Authority authority) {
        ConcurrentMap<String, Authority> map = AUTHORITIES.get(authority.getGroup().name());
        map.put(authority.getCode(), authority);
    }

    public static Authority get(Authority authority) {
        return (Authority) AUTHORITIES.get(authority.getCode());
    }


    // private val authorityMap:ConcurrentHashMap<String, Authority> =
    //
    // ConcurrentHashMap()
    //
    // fun putAuthority(code:String, name:String, group:AuthorityGroupEnum) {
    //     authorityMap[code] = Authority(code, name, group)
    // }
    //
    // fun putAuthority(authority:Authority) {
    //     authorityMap[authority.code] = Authority(authority.code, authority.name, authority.group)
    // }
    //
    // fun getAuthorityList():List<Authority>
    //
    // {
    //     return authorityMap.values.toList()
    // }
    //
    // fun getAuthorityCodeList():List<String>
    //
    // {
    //     return authorityMap.values.map {
    //     it.code
    // }.toList()
    // }
    //
    // fun getAuthorityGroup():Map<AuthorityGroupEnum, List<Authority>>
    //
    // {
    //     return getAuthorityList().groupBy {
    //     it.group
    // }
    // }
    //
    // fun getAuthorityGroupList():List<AuthorityGroup>
    //
    // {
    //     val authorityGroupMap = getAuthorityGroup()
    //     val authorityGroupList = arrayListOf < AuthorityGroup > ()
    //     authorityGroupMap.keys.forEach {
    //     authorityGroupList.add(AuthorityGroup(it, authorityGroupMap.getValue(it)))
    // }
    //     return authorityGroupList
    // }
    //
    // fun getAuthorityByCode(code:String):
    //
    // Authority {
    //     return authorityMap[code] !!
    // }

}
