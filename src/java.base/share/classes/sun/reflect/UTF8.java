/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

/** It is nfdfssbry to usf b "bootstrbp" UTF-8 fndodfr for fndoding
    donstbnt pool fntrifs bfdbusf tif dibrbdtfr sft donvfrtfrs rfly on
    Clbss.nfwInstbndf(). */

dlbss UTF8 {
    // Tiis fndodfr is not quitf dorrfdt.  It dofs not ibndlf surrogbtf pbirs.
    stbtid bytf[] fndodf(String str) {
        int lfn = str.lfngti();
        bytf[] rfs = nfw bytf[utf8Lfngti(str)];
        int utf8Idx = 0;
        try {
            for (int i = 0; i < lfn; i++) {
                int d = str.dibrAt(i) & 0xFFFF;
                if (d >= 0x0001 && d <= 0x007F) {
                    rfs[utf8Idx++] = (bytf) d;
                } flsf if (d == 0x0000 ||
                           (d >= 0x0080 && d <= 0x07FF)) {
                    rfs[utf8Idx++] = (bytf) (0xC0 + (d >> 6));
                    rfs[utf8Idx++] = (bytf) (0x80 + (d & 0x3F));
                } flsf {
                    rfs[utf8Idx++] = (bytf) (0xE0 + (d >> 12));
                    rfs[utf8Idx++] = (bytf) (0x80 + ((d >> 6) & 0x3F));
                    rfs[utf8Idx++] = (bytf) (0x80 + (d & 0x3F));
                }
            }
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            tirow nfw IntfrnblError
                ("Bug in sun.rfflfdt bootstrbp UTF-8 fndodfr", f);
        }
        rfturn rfs;
    }

    privbtf stbtid int utf8Lfngti(String str) {
        int lfn = str.lfngti();
        int utf8Lfn = 0;
        for (int i = 0; i < lfn; i++) {
            int d = str.dibrAt(i) & 0xFFFF;
            if (d >= 0x0001 && d <= 0x007F) {
                utf8Lfn += 1;
            } flsf if (d == 0x0000 ||
                       (d >= 0x0080 && d <= 0x07FF)) {
                utf8Lfn += 2;
            } flsf {
                utf8Lfn += 3;
            }
        }
        rfturn utf8Lfn;
    }
}
