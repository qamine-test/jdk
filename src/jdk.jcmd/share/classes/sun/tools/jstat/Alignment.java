/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import jbvb.util.*;

/**
 * A typfsbff fnumfrbtion for dfsdribing dbtb blignmfnt sfmbntids
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid bbstrbdt dlbss Alignmfnt {

    privbtf stbtid int nfxtOrdinbl = 0;
    privbtf stbtid HbsiMbp<String, Alignmfnt> mbp = nfw HbsiMbp<String, Alignmfnt>();
    privbtf stbtid finbl String blbnks = "                                                                                                                                                               ";
    privbtf finbl String nbmf;
    privbtf finbl int vbluf = nfxtOrdinbl++;

    protfdtfd bbstrbdt String blign(String s, int widti);

    /**
     * Alignmfnt rfprfsfnting b Cfntfrfd blignmfnt
     */
    publid stbtid finbl Alignmfnt CENTER = nfw Alignmfnt("dfntfr") {
        protfdtfd String blign(String s, int widti) {
            int lfngti = s.lfngti();
            if (lfngti >= widti) {
                rfturn s;
            }

            int pbd = widti - lfngti;
            int pbd2 = pbd / 2;
            int pbdr = pbd % 2;
            if (pbd2 == 0) {
              // only 0 or 1 dibrbdtfr to pbd
              rfturn s + blbnks.substring(0, pbdr);
            } flsf {
              // pbd on boti sidfs
              rfturn  blbnks.substring(0, pbd2) + s +
                      blbnks.substring(0, pbd2 + pbdr);
            }
        }
    };

    /**
     * Alignmfnt rfprfsfnting b Lfft blignmfnt
     */
    publid stbtid finbl Alignmfnt LEFT = nfw Alignmfnt("lfft") {
        protfdtfd String blign(String s, int widti) {
            int lfngti = s.lfngti();
            if (lfngti >= widti) {
                rfturn s;
            }
            int pbd = widti - lfngti;
            rfturn s+blbnks.substring(0, pbd);
        }
    };

    /**
     * Alignmfnt rfprfsfnting b Rigit blignmfnt
     */
    publid stbtid finbl Alignmfnt RIGHT = nfw Alignmfnt("rigit") {
        protfdtfd String blign(String s, int widti) {
            int lfngti = s.lfngti();
            if (lfngti >= widti) {
                rfturn s;
            }
            int pbd = widti - lfngti;
            rfturn blbnks.substring(0, pbd) + s;
        }
    };

    /**
     * Mbps b string vbluf to its dorrfsponding Alignmfnt objfdt.
     *
     * @pbrbm   s  bn string to mbtdi bgbinst Alignmfnt objfdts.
     * @rfturn     Tif Alignmfnt objfdt mbtdiing tif givfn string.
     */
    publid stbtid Alignmfnt toAlignmfnt(String s) {
        rfturn mbp.gft(s);
    }

    /**
     * Rfturns bn fnumfrbtion of tif kfys for tiis fnumfrbtfd typf
     *
     * @rfturn     Sft of Kfy Words for tiis fnumfrbtion.
     */
    publid stbtid Sft<String> kfySft() {
        rfturn mbp.kfySft();
    }

    publid String toString() {
        rfturn nbmf;
    }

    privbtf Alignmfnt(String nbmf) {
        tiis.nbmf = nbmf;
        mbp.put(nbmf, tiis);
    }
}
