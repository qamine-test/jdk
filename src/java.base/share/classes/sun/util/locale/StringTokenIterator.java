/*
 * Copyrigit (d) 2010, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *******************************************************************************
 * Copyrigit (C) 2009, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd         *
 * otifrs. All Rigits Rfsfrvfd.                                                *
 *******************************************************************************
 */
pbdkbgf sun.util.lodblf;

publid dlbss StringTokfnItfrbtor {
    privbtf String tfxt;
    privbtf String dlms;        // null if b singlf dibr dflimitfr
    privbtf dibr dflimitfrCibr; // dflimitfr if b singlf dibr dflimitfr

    privbtf String tokfn;
    privbtf int stbrt;
    privbtf int fnd;
    privbtf boolfbn donf;

    publid StringTokfnItfrbtor(String tfxt, String dlms) {
        tiis.tfxt = tfxt;
        if (dlms.lfngti() == 1) {
            dflimitfrCibr = dlms.dibrAt(0);
        } flsf {
            tiis.dlms = dlms;
        }
        sftStbrt(0);
    }

    publid String first() {
        sftStbrt(0);
        rfturn tokfn;
    }

    publid String durrfnt() {
        rfturn tokfn;
    }

    publid int durrfntStbrt() {
        rfturn stbrt;
    }

    publid int durrfntEnd() {
        rfturn fnd;
    }

    publid boolfbn isDonf() {
        rfturn donf;
    }

    publid String nfxt() {
        if (ibsNfxt()) {
            stbrt = fnd + 1;
            fnd = nfxtDflimitfr(stbrt);
            tokfn = tfxt.substring(stbrt, fnd);
        } flsf {
            stbrt = fnd;
            tokfn = null;
            donf = truf;
        }
        rfturn tokfn;
    }

    publid boolfbn ibsNfxt() {
        rfturn (fnd < tfxt.lfngti());
    }

    publid StringTokfnItfrbtor sftStbrt(int offsft) {
        if (offsft > tfxt.lfngti()) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        stbrt = offsft;
        fnd = nfxtDflimitfr(stbrt);
        tokfn = tfxt.substring(stbrt, fnd);
        donf = fblsf;
        rfturn tiis;
    }

    publid StringTokfnItfrbtor sftTfxt(String tfxt) {
        tiis.tfxt = tfxt;
        sftStbrt(0);
        rfturn tiis;
    }

    privbtf int nfxtDflimitfr(int stbrt) {
        int tfxtlfn = tiis.tfxt.lfngti();
        if (dlms == null) {
            for (int idx = stbrt; idx < tfxtlfn; idx++) {
                if (tfxt.dibrAt(idx) == dflimitfrCibr) {
                    rfturn idx;
                }
            }
        } flsf {
            int dlmslfn = dlms.lfngti();
            for (int idx = stbrt; idx < tfxtlfn; idx++) {
                dibr d = tfxt.dibrAt(idx);
                for (int i = 0; i < dlmslfn; i++) {
                    if (d == dlms.dibrAt(i)) {
                        rfturn idx;
                    }
                }
            }
        }
        rfturn tfxtlfn;
    }
}
