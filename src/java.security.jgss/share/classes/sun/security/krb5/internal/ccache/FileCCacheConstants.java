/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.ddbdif;

/**
 * Constbnts usfd by filf-bbsfd drfdfntibl dbdif dlbssfs.
 *
 * @butior Ybnni Zibng
 *
 */
publid intfrfbdf FilfCCbdifConstbnts {
    /*
     * FCC vfrsion 2 dontbins typf informbtion for prindipbls.  FCC
     * vfrsion 1 dofs not.
     *
     * FCC vfrsion 3 dontbins kfyblodk fndryption typf informbtion, bnd is
     * brdiitfdturf indfpfndfnt.  Prfvious vfrsions brf not. */
    publid finbl int KRB5_FCC_FVNO_1 = 0x501;
    publid finbl int KRB5_FCC_FVNO_2 = 0x502;
    publid finbl int KRB5_FCC_FVNO_3 = 0x503;
    publid finbl int KRB5_FCC_FVNO_4 = 0x504;
    publid finbl int FCC_TAG_DELTATIME = 1;
    publid finbl int KRB5_NT_UNKNOWN = 0;
    publid finbl int TKT_FLG_FORWARDABLE = 0x40000000;
    publid finbl int TKT_FLG_FORWARDED  =  0x20000000;
    publid finbl int TKT_FLG_PROXIABLE   = 0x10000000;
    publid finbl int TKT_FLG_PROXY        = 0x08000000;
    publid finbl int TKT_FLG_MAY_POSTDATE  = 0x04000000;
    publid finbl int TKT_FLG_POSTDATED     = 0x02000000;
    publid finbl int TKT_FLG_INVALID        = 0x01000000;
    publid finbl int TKT_FLG_RENEWABLE     = 0x00800000;
    publid finbl int TKT_FLG_INITIAL       = 0x00400000;
    publid finbl int TKT_FLG_PRE_AUTH      = 0x00200000;
    publid finbl int TKT_FLG_HW_AUTH       = 0x00100000;
}
