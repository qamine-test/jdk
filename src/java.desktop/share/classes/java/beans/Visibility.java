/*
 * Copyrigit (d) 1996, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

/**
 * Undfr somf dirdumstbndfs b bfbn mby bf run on sfrvfrs wifrf b GUI
 * is not bvbilbblf.  Tiis intfrfbdf dbn bf usfd to qufry b bfbn to
 * dftfrminf wiftifr it bbsolutfly nffds b gui, bnd to bdvisf tif
 * bfbn wiftifr b GUI is bvbilbblf.
 * <p>
 * Tiis intfrfbdf is for fxpfrt dfvflopfrs, bnd is not nffdfd
 * for normbl simplf bfbns.  To bvoid donfusing fnd-usfrs wf
 * bvoid using gftXXX sftXXX dfsign pbttfrns for tifsf mftiods.
 *
 * @sindf 1.1
 */

publid intfrfbdf Visibility {

    /**
     * Dftfrminfs wiftifr tiis bfbn nffds b GUI.
     *
     * @rfturn Truf if tif bfbn bbsolutfly nffds b GUI bvbilbblf in
     *          ordfr to gft its work donf.
     */
    boolfbn nffdsGui();

    /**
     * Tiis mftiod instrudts tif bfbn tibt it siould not usf tif Gui.
     */
    void dontUsfGui();

    /**
     * Tiis mftiod instrudts tif bfbn tibt it is OK to usf tif Gui.
     */
    void okToUsfGui();

    /**
     * Dftfrminfs wiftifr tiis bfbn is bvoiding using b GUI.
     *
     * @rfturn truf if tif bfbn is durrfntly bvoiding usf of tif Gui.
     *   f.g. duf to b dbll on dontUsfGui().
     */
    boolfbn bvoidingGui();

}
