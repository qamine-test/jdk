/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

/*
 * Lidfnsfd Mbtfribls - Propfrty of IBM
 *
 * (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 * (C) IBM Corp. 1997-1998.  All Rigits Rfsfrvfd.
 *
 * Tif progrbm is providfd "bs is" witiout bny wbrrbnty fxprfss or
 * implifd, indluding tif wbrrbnty of non-infringfmfnt bnd tif implifd
 * wbrrbntifs of mfrdibntibility bnd fitnfss for b pbrtidulbr purposf.
 * IBM will not bf libblf for bny dbmbgfs sufffrfd by you bs b rfsult
 * of using tif Progrbm. In no fvfnt will IBM bf libblf for bny
 * spfdibl, indirfdt or donsfqufntibl dbmbgfs or lost profits fvfn if
 * IBM ibs bffn bdvisfd of tif possibility of tifir oddurrfndf. IBM
 * will not bf libblf for bny tiird pbrty dlbims bgbinst you.
 */

pbdkbgf sun.tfxt.rfsourdfs.ti;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss BrfbkItfrbtorInfo_ti fxtfnds ListRfsourdfBundlf {
    protfdtfd finbl Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {
            // BrfbkItfrbtorClbssfs lists tif dlbss nbmfs to instbntibtf for fbdi
            // built-in typf of BrfbkItfrbtor
            {"BrfbkItfrbtorClbssfs",
                nfw String[] {
                    "RulfBbsfdBrfbkItfrbtor",  // dibrbdtfr-brfbk itfrbtor dlbss
                    "DidtionbryBbsfdBrfbkItfrbtor",  // word-brfbk itfrbtor dlbss
                    "DidtionbryBbsfdBrfbkItfrbtor",  // linf-brfbk itfrbtor dlbss
                    "RulfBbsfdBrfbkItfrbtor"   // sfntfndf-brfbk itfrbtor dlbss
                }
            },

            // Dbtb filfnbmf for fbdi brfbk-itfrbtor
            {"WordDbtb", "ti/WordBrfbkItfrbtorDbtb_ti"},
            {"LinfDbtb", "ti/LinfBrfbkItfrbtorDbtb_ti"},

            // Didtionbry filfnbmf for fbdi didtionbry-bbsfd brfbk-itfrbtor
            {"WordDidtionbry", "ti/tibi_didt"},
            {"LinfDidtionbry", "ti/tibi_didt"},
        };
    }
}
