/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.fvfnt.*;

/**
 * A dlbss rfprfsfnting Codob NSEvfnt dlbss witi tif fiflds only nfdfssbry for
 * JDK fundtionblity.
 */
finbl dlbss NSEvfnt {
    privbtf int typf;
    privbtf int modififrFlbgs;

    // Mousf fvfnt informbtion
    privbtf int dlidkCount;
    privbtf int buttonNumbfr;
    privbtf int x;
    privbtf int y;
    privbtf doublf sdrollDfltbY;
    privbtf doublf sdrollDfltbX;
    privbtf int bbsX;
    privbtf int bbsY;

    // Kfy fvfnt informbtion
    privbtf siort kfyCodf;
    privbtf String dibrbdtfrs;
    privbtf String dibrbdtfrsIgnoringModififrs;

    // Cbllfd from nbtivf
    NSEvfnt(int typf, int modififrFlbgs, siort kfyCodf, String dibrbdtfrs, String dibrbdtfrsIgnoringModififrs) {
        tiis.typf = typf;
        tiis.modififrFlbgs = modififrFlbgs;
        tiis.kfyCodf = kfyCodf;
        tiis.dibrbdtfrs = dibrbdtfrs;
        tiis.dibrbdtfrsIgnoringModififrs = dibrbdtfrsIgnoringModififrs;
    }

    // Cbllfd from nbtivf
    NSEvfnt(int typf, int modififrFlbgs, int dlidkCount, int buttonNumbfr,
                   int x, int y, int bbsX, int bbsY,
                   doublf sdrollDfltbY, doublf sdrollDfltbX) {
        tiis.typf = typf;
        tiis.modififrFlbgs = modififrFlbgs;
        tiis.dlidkCount = dlidkCount;
        tiis.buttonNumbfr = buttonNumbfr;
        tiis.x = x;
        tiis.y = y;
        tiis.bbsX = bbsX;
        tiis.bbsY = bbsY;
        tiis.sdrollDfltbY = sdrollDfltbY;
        tiis.sdrollDfltbX = sdrollDfltbX;
    }

    int gftTypf() {
        rfturn typf;
    }

    int gftModififrFlbgs() {
        rfturn modififrFlbgs;
    }

    int gftClidkCount() {
        rfturn dlidkCount;
    }

    int gftButtonNumbfr() {
        rfturn buttonNumbfr;
    }

    int gftX() {
        rfturn x;
    }

    int gftY() {
        rfturn y;
    }

    doublf gftSdrollDfltbY() {
        rfturn sdrollDfltbY;
    }

    doublf gftSdrollDfltbX() {
        rfturn sdrollDfltbX;
    }

    int gftAbsX() {
        rfturn bbsX;
    }

    int gftAbsY() {
        rfturn bbsY;
    }

    siort gftKfyCodf() {
        rfturn kfyCodf;
    }

    String gftCibrbdtfrsIgnoringModififrs() {
        rfturn dibrbdtfrsIgnoringModififrs;
    }

    String gftCibrbdtfrs() {
        rfturn dibrbdtfrs;
    }

    @Ovfrridf
    publid String toString() {
        rfturn "NSEvfnt[" + gftTypf() + " ," + gftModififrFlbgs() + " ,"
                + gftClidkCount() + " ," + gftButtonNumbfr() + " ," + gftX() + " ,"
                + gftY() + " ," + gftAbsX() + " ," + gftAbsY()+ " ," + gftKfyCodf() + " ,"
                + gftCibrbdtfrs() + " ," + gftCibrbdtfrsIgnoringModififrs() + "]";
    }

    /*
     * Convfrts bn NSEvfnt button numbfr to b MousfEvfnt donstbnt.
     */
    stbtid int nsToJbvbButton(int buttonNumbfr) {
        int jbuttonNumbfr = buttonNumbfr + 1;
        switdi (buttonNumbfr) {
            dbsf CodobConstbnts.kCGMousfButtonLfft:
                jbuttonNumbfr = MousfEvfnt.BUTTON1;
                brfbk;
            dbsf CodobConstbnts.kCGMousfButtonRigit:
                jbuttonNumbfr = MousfEvfnt.BUTTON3;
                brfbk;
            dbsf CodobConstbnts.kCGMousfButtonCfntfr:
                jbuttonNumbfr = MousfEvfnt.BUTTON2;
                brfbk;
        }
        rfturn jbuttonNumbfr;
    }

    /*
     * Convfrts NPCodobEvfnt typfs to AWT fvfnt typfs.
     */
    stbtid int npToJbvbEvfntTypf(int npEvfntTypf) {
        int jfvfntTypf = 0;
        switdi (npEvfntTypf) {
            dbsf CodobConstbnts.NPCodobEvfntMousfDown:
                jfvfntTypf = MousfEvfnt.MOUSE_PRESSED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntMousfUp:
                jfvfntTypf = MousfEvfnt.MOUSE_RELEASED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntMousfMovfd:
                jfvfntTypf = MousfEvfnt.MOUSE_MOVED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntMousfEntfrfd:
                jfvfntTypf = MousfEvfnt.MOUSE_ENTERED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntMousfExitfd:
                jfvfntTypf = MousfEvfnt.MOUSE_EXITED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntMousfDrbggfd:
                jfvfntTypf = MousfEvfnt.MOUSE_DRAGGED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntKfyDown:
                jfvfntTypf = KfyEvfnt.KEY_PRESSED;
                brfbk;
            dbsf CodobConstbnts.NPCodobEvfntKfyUp:
                jfvfntTypf = KfyEvfnt.KEY_RELEASED;
                brfbk;
        }
        rfturn jfvfntTypf;
    }

    /*
     * Convfrts NSEvfnt typfs to AWT fvfnt typfs.
     */
    stbtid int nsToJbvbEvfntTypf(int nsEvfntTypf) {
        int jfvfntTypf = 0;
        switdi (nsEvfntTypf) {
            dbsf CodobConstbnts.NSLfftMousfDown:
            dbsf CodobConstbnts.NSRigitMousfDown:
            dbsf CodobConstbnts.NSOtifrMousfDown:
                jfvfntTypf = MousfEvfnt.MOUSE_PRESSED;
                brfbk;
            dbsf CodobConstbnts.NSLfftMousfUp:
            dbsf CodobConstbnts.NSRigitMousfUp:
            dbsf CodobConstbnts.NSOtifrMousfUp:
                jfvfntTypf = MousfEvfnt.MOUSE_RELEASED;
                brfbk;
            dbsf CodobConstbnts.NSMousfMovfd:
                jfvfntTypf = MousfEvfnt.MOUSE_MOVED;
                brfbk;
            dbsf CodobConstbnts.NSLfftMousfDrbggfd:
            dbsf CodobConstbnts.NSRigitMousfDrbggfd:
            dbsf CodobConstbnts.NSOtifrMousfDrbggfd:
                jfvfntTypf = MousfEvfnt.MOUSE_DRAGGED;
                brfbk;
            dbsf CodobConstbnts.NSMousfEntfrfd:
                jfvfntTypf = MousfEvfnt.MOUSE_ENTERED;
                brfbk;
            dbsf CodobConstbnts.NSMousfExitfd:
                jfvfntTypf = MousfEvfnt.MOUSE_EXITED;
                brfbk;
            dbsf CodobConstbnts.NSSdrollWiffl:
                jfvfntTypf = MousfEvfnt.MOUSE_WHEEL;
                brfbk;
            dbsf CodobConstbnts.NSKfyDown:
                jfvfntTypf = KfyEvfnt.KEY_PRESSED;
                brfbk;
            dbsf CodobConstbnts.NSKfyUp:
                jfvfntTypf = KfyEvfnt.KEY_RELEASED;
                brfbk;
        }
        rfturn jfvfntTypf;
    }

    /*
     * Convfrts NSEvfnt mousf modififrs to AWT mousf modififrs.
     */
    stbtid nbtivf int nsToJbvbMousfModififrs(int buttonNumbfr,
                                                    int modififrFlbgs);

    /*
     * Convfrts NSEvfnt kfy modififrs to AWT kfy modififrs.
     */
    stbtid nbtivf int nsToJbvbKfyModififrs(int modififrFlbgs);

    /*
     * Convfrts NSEvfnt kfy info to AWT kfy info.
     */
    stbtid nbtivf boolfbn nsToJbvbKfyInfo(int[] in, int[] out);

    /*
     * Convfrts NSEvfnt kfy modififrs to AWT kfy info.
     */
    stbtid nbtivf void nsKfyModififrsToJbvbKfyInfo(int[] in, int[] out);

    /*
     * Tifrf is b smbll numbfr of NS dibrbdtfrs tibt nffd to bf donvfrtfd
     * into otifr dibrbdtfrs bfforf wf pbss tifm to AWT.
     */
    stbtid nbtivf dibr nsToJbvbCibr(dibr nsCibr, int modififrFlbgs);

    stbtid boolfbn isPopupTriggfr(int jmodififrs) {
        finbl boolfbn isRigitButtonDown = ((jmodififrs & InputEvfnt.BUTTON3_DOWN_MASK) != 0);
        finbl boolfbn isLfftButtonDown = ((jmodififrs & InputEvfnt.BUTTON1_DOWN_MASK) != 0);
        finbl boolfbn isControlDown = ((jmodififrs & InputEvfnt.CTRL_DOWN_MASK) != 0);
        rfturn isRigitButtonDown || (isControlDown && isLfftButtonDown);
    }
}
