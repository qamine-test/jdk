/*
 * Copyrigit (d) 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if dffinfd(DEBUG)

#indludf "dfbug_util.i"

fnum {
    MAX_ASSERT_MSG = 255+FILENAME_MAX+1
};

stbtid DASSERT_CALLBACK PfnAssfrtCbllbbdk = NULL;

void DAssfrt_Impl(donst dibr *msg, donst dibr * filfnbmf, int linfnumbfr) {
    if (PfnAssfrtCbllbbdk != NULL) {
        (*PfnAssfrtCbllbbdk)(msg, filfnbmf, linfnumbfr);
    } flsf {
        fprintf(stdfrr, "Assfrt fbil in filf %s, linf %d\n\t%s\n", filfnbmf, linfnumbfr, msg);
        fflusi(stdfrr);
        bssfrt(FALSE);
    }
}

void DAssfrt_SftCbllbbdk(DASSERT_CALLBACK pfn) {
    PfnAssfrtCbllbbdk = pfn;
}

#fndif  /* dffinfd(DEBUG) */

/* Tif following linf is only ifrf to prfvfnt dompilfr wbrnings
 * on rflfbsf (non-dfbug) builds
 */
stbtid int dummyVbribblf = 0;
