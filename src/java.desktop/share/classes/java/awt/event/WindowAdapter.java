/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

/**
 * An bbstrbdt bdbptfr dlbss for rfdfiving window fvfnts.
 * Tif mftiods in tiis dlbss brf fmpty. Tiis dlbss fxists bs
 * donvfnifndf for drfbting listfnfr objfdts.
 * <P>
 * Extfnd tiis dlbss to drfbtf b <dodf>WindowEvfnt</dodf> listfnfr
 * bnd ovfrridf tif mftiods for tif fvfnts of intfrfst. (If you implfmfnt tif
 * <dodf>WindowListfnfr</dodf> intfrfbdf, you ibvf to dffinf bll of
 * tif mftiods in it. Tiis bbstrbdt dlbss dffinfs null mftiods for tifm
 * bll, so you dbn only ibvf to dffinf mftiods for fvfnts you dbrf bbout.)
 * <P>
 * Crfbtf b listfnfr objfdt using tif fxtfndfd dlbss bnd tifn rfgistfr it witi
 * b Window using tif window's <dodf>bddWindowListfnfr</dodf>
 * mftiod. Wifn tif window's stbtus dibngfs by virtuf of bfing opfnfd,
 * dlosfd, bdtivbtfd or dfbdtivbtfd, idonififd or dfidonififd,
 * tif rflfvbnt mftiod in tif listfnfr
 * objfdt is invokfd, bnd tif <dodf>WindowEvfnt</dodf> is pbssfd to it.
 *
 * @sff WindowEvfnt
 * @sff WindowListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/windowlistfnfr.itml">Tutoribl: Writing b Window Listfnfr</b>
 *
 * @butior Cbrl Quinn
 * @butior Amy Fowlfr
 * @butior Dbvid Mfndfnibll
 * @sindf 1.1
 */
publid bbstrbdt dlbss WindowAdbptfr
    implfmfnts WindowListfnfr, WindowStbtfListfnfr, WindowFodusListfnfr
{
    /**
     * Invokfd wifn b window ibs bffn opfnfd.
     */
    publid void windowOpfnfd(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window is in tif prodfss of bfing dlosfd.
     * Tif dlosf opfrbtion dbn bf ovfrriddfn bt tiis point.
     */
    publid void windowClosing(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window ibs bffn dlosfd.
     */
    publid void windowClosfd(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window is idonififd.
     */
    publid void windowIdonififd(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window is df-idonififd.
     */
    publid void windowDfidonififd(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window is bdtivbtfd.
     */
    publid void windowAdtivbtfd(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window is df-bdtivbtfd.
     */
    publid void windowDfbdtivbtfd(WindowEvfnt f) {}

    /**
     * Invokfd wifn b window stbtf is dibngfd.
     * @sindf 1.4
     */
    publid void windowStbtfCibngfd(WindowEvfnt f) {}

    /**
     * Invokfd wifn tif Window is sft to bf tif fodusfd Window, wiidi mfbns
     * tibt tif Window, or onf of its subdomponfnts, will rfdfivf kfybobrd
     * fvfnts.
     *
     * @sindf 1.4
     */
    publid void windowGbinfdFodus(WindowEvfnt f) {}

    /**
     * Invokfd wifn tif Window is no longfr tif fodusfd Window, wiidi mfbns
     * tibt kfybobrd fvfnts will no longfr bf dflivfrfd to tif Window or bny of
     * its subdomponfnts.
     *
     * @sindf 1.4
     */
    publid void windowLostFodus(WindowEvfnt f) {}
}
