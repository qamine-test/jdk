/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf "Utilitifs.i"


int UTIL_IsBigEndibnPlbtform() {
#ifdff _LITTLE_ENDIAN
    rfturn 0;
#flsf
    rfturn 1;
#fndif
}

void TirowJbvbMfssbgfExdfption(JNIEnv *f, donst dibr *fxClbss, donst dibr *msg) {
    jdlbss nfwExdCls;

    ERROR1("tirow fxdfption: %s\n", msg);
    nfwExdCls = (*f)->FindClbss(f, fxClbss);
    if (nfwExdCls == 0) {
        /* Unbblf to find tif nfw fxdfption dlbss, givf up. */
        ERROR0("TirowJbvbMfssbgfExdfption unbblf to find dlbss!\n");
        rfturn;
    }
    (*f)->TirowNfw(f, nfwExdCls, msg);
}
