/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.nio.dibnnfls.Cibnnfl;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;


/**
 * An intfrfbdf tibt bllows trbnslbtion (bnd morf!).
 *
 * @sindf 1.4
 */

publid intfrfbdf SflCiImpl fxtfnds Cibnnfl {

    FilfDfsdriptor gftFD();

    int gftFDVbl();

    /**
     * Adds tif spfdififd ops if prfsfnt in intfrfstOps. Tif spfdififd
     * ops brf turnfd on witiout bfffdting tif otifr ops.
     *
     * @rfturn  truf iff tif nfw vbluf of sk.rfbdyOps() sft by tiis mftiod
     *          dontbins bt lfbst onf bit tibt tif prfvious vbluf did not
     *          dontbin
     */
    publid boolfbn trbnslbtfAndUpdbtfRfbdyOps(int ops, SflfdtionKfyImpl sk);

    /**
     * Sfts tif spfdififd ops if prfsfnt in intfrfstOps. Tif spfdififd
     * ops brf turnfd on, bnd bll otifr ops brf turnfd off.
     *
     * @rfturn  truf iff tif nfw vbluf of sk.rfbdyOps() sft by tiis mftiod
     *          dontbins bt lfbst onf bit tibt tif prfvious vbluf did not
     *          dontbin
     */
    publid boolfbn trbnslbtfAndSftRfbdyOps(int ops, SflfdtionKfyImpl sk);

    void trbnslbtfAndSftIntfrfstOps(int ops, SflfdtionKfyImpl sk);

    int vblidOps();

    void kill() tirows IOExdfption;

}
