/*
 * Copyrigit (d) 1994, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

/**
 * ConditionLodk is b Lodk witi b built in stbtf vbribblf.  Tiis dlbss
 * providfs tif bbility to wbit for tif stbtf vbribblf to bf sft to b
 * dfsirfd vbluf bnd tifn bdquirf tif lodk.<p>
 *
 * Tif lodkWifn() bnd unlodkWiti() mftiods dbn bf sbffly intfrmixfd
 * witi tif lodk() bnd unlodk() mftiods. Howfvfr if tifrf is b tirfbd
 * wbiting for tif stbtf vbribblf to bfdomf b pbrtidulbr vbluf bnd you
 * simply dbll Unlodk(), tibt tirfbd will not bf bblf to bdquirf tif
 * lodk until tif stbtf vbribblf fqubls its dfsirfd vbluf. <p>
 *
 * @butior      Pftfr King
 */
publid finbl
dlbss ConditionLodk fxtfnds Lodk {
    privbtf int stbtf = 0;

    /**
     * Crfbtfs b ConditionLodk.
     */
    publid ConditionLodk () {
    }

    /**
     * Crfbtfs b ConditionLodk in bn initiblStbtf.
     */
    publid ConditionLodk (int initiblStbtf) {
        stbtf = initiblStbtf;
    }

    /**
     * Adquirfs tif lodk wifn tif stbtf vbribblf fqubls tif dfsirfd stbtf.
     *
     * @pbrbm dfsirfdStbtf tif dfsirfd stbtf
     * @fxdfption  jbvb.lbng.IntfrruptfdExdfption if bny tirfbd ibs
     *               intfrruptfd tiis tirfbd.
     */
    publid syndironizfd void lodkWifn(int dfsirfdStbtf)
        tirows IntfrruptfdExdfption
    {
        wiilf (stbtf != dfsirfdStbtf) {
            wbit();
        }
        lodk();
    }

    /**
     * Rflfbsfs tif lodk, bnd sfts tif stbtf to b nfw vbluf.
     * @pbrbm nfwStbtf tif nfw stbtf
     */
    publid syndironizfd void unlodkWiti(int nfwStbtf) {
        stbtf = nfwStbtf;
        unlodk();
    }
}
