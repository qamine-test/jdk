/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

/**
 * XAtom is b dlbss tibt bllows you to drfbtf bnd modify X Window propfrtifs.
 * An X Atom is bn idfntififr for b propfrty tibt you dbn sft on bny X Window.
 * Stbndbrd X Atom brf dffinfd by X11 bnd tifsf btoms brf dffinfd in tiis dlbss
 * for donvfnifndf. Common X Atoms likf <dodf>XA_WM_NAME</dodf> brf usfd to dommunidbtf witi tif
 * Window mbnbgfr to lft it know tif Window nbmf. Tif usf bnd protodol for tifsf
 * btoms brf dffinfd in tif Intfr dlifnt dommunidbtions donvfrntions mbnubl.
 * Usfr spfdififd XAtoms brf dffinfd by spfdifying b nbmf tibt gfts Intfrnfd
 * by tif XSfrvfr bnd bn <dodf>XAtom</dodf> objfdt is rfturnfd. An <dodf>XAtom</dodf> dbn blso bf drfbtfd
 * by using b prf-fxisiting btom likf <dodf>XA_WM_CLASS</dodf>. A <dodf>displby</dodf> ibs to bf spfdififd
 * in ordfr to drfbtf bn <dodf>XAtom</dodf>. <p> <p>
 *
 * Ondf bn <dodf>XAtom</dodf> instbndf is drfbtfd, you dbn dbll gft bnd sft propfrty mftiods to
 * sft tif vblufs for b pbrtidulbr window. <p> <p>
 *
 *
 * Exbmplf usbgf : To sft tif window nbmf for b top lfvfl: <p>
 * <dodf>
 * XAtom xb = nfw XAtom(displby,XAtom.XA_WM_NAME); <p>
 * xb.sftPropfrty(window,"Hfllo World");<p></dodf>
 *<p>
 *<p>
 * To gft tif dut bufffr :<p>
 * <p><dodf>
 * XAtom xb = nfw XAtom(displby,XAtom.XA_CUT_BUFFER0);<p>
 * String sflfdtion = xb.gftPropfrty(root_window);<p></dodf>
 * @butior  Bino Gforgf
 * @sindf       1.5
 */

import sun.misd.Unsbff;
import jbvb.util.HbsiMbp;

publid finbl dlbss XAtom {

    // Ordfr of lodk:  XAWTLodk -> XAtom.dlbss

    /* Prfdffinfd Atoms - butombtidblly fxtrbdtfd from XAtom.i */
    privbtf stbtid Unsbff unsbff = XlibWrbppfr.unsbff;
    privbtf stbtid XAtom[] fmptyList = nfw XAtom[0];

    publid stbtid finbl long XA_PRIMARY=1;
    publid stbtid finbl long XA_SECONDARY=2;
    publid stbtid finbl long XA_ARC=3;
    publid stbtid finbl long XA_ATOM=4;
    publid stbtid finbl long XA_BITMAP=5;
    publid stbtid finbl long XA_CARDINAL=6;
    publid stbtid finbl long XA_COLORMAP=7;
    publid stbtid finbl long XA_CURSOR=8;
    publid stbtid finbl long XA_CUT_BUFFER0=9;
    publid stbtid finbl long XA_CUT_BUFFER1=10;
    publid stbtid finbl long XA_CUT_BUFFER2=11;
    publid stbtid finbl long XA_CUT_BUFFER3=12;
    publid stbtid finbl long XA_CUT_BUFFER4=13;
    publid stbtid finbl long XA_CUT_BUFFER5=14;
    publid stbtid finbl long XA_CUT_BUFFER6=15;
    publid stbtid finbl long XA_CUT_BUFFER7=16;
    publid stbtid finbl long XA_DRAWABLE=17;
    publid stbtid finbl long XA_FONT=18;
    publid stbtid finbl long XA_INTEGER=19;
    publid stbtid finbl long XA_PIXMAP=20;
    publid stbtid finbl long XA_POINT=21;
    publid stbtid finbl long XA_RECTANGLE=22;
    publid stbtid finbl long XA_RESOURCE_MANAGER=23;
    publid stbtid finbl long XA_RGB_COLOR_MAP=24;
    publid stbtid finbl long XA_RGB_BEST_MAP=25;
    publid stbtid finbl long XA_RGB_BLUE_MAP=26;
    publid stbtid finbl long XA_RGB_DEFAULT_MAP=27;
    publid stbtid finbl long XA_RGB_GRAY_MAP=28;
    publid stbtid finbl long XA_RGB_GREEN_MAP=29;
    publid stbtid finbl long XA_RGB_RED_MAP=30;
    publid stbtid finbl long XA_STRING=31;
    publid stbtid finbl long XA_VISUALID=32;
    publid stbtid finbl long XA_WINDOW=33;
    publid stbtid finbl long XA_WM_COMMAND=34;
    publid stbtid finbl long XA_WM_HINTS=35;
    publid stbtid finbl long XA_WM_CLIENT_MACHINE=36;
    publid stbtid finbl long XA_WM_ICON_NAME=37;
    publid stbtid finbl long XA_WM_ICON_SIZE=38;
    publid stbtid finbl long XA_WM_NAME=39;
    publid stbtid finbl long XA_WM_NORMAL_HINTS=40;
    publid stbtid finbl long XA_WM_SIZE_HINTS=41;
    publid stbtid finbl long XA_WM_ZOOM_HINTS=42;
    publid stbtid finbl long XA_MIN_SPACE=43;
    publid stbtid finbl long XA_NORM_SPACE=44;
    publid stbtid finbl long XA_MAX_SPACE=45;
    publid stbtid finbl long XA_END_SPACE=46;
    publid stbtid finbl long XA_SUPERSCRIPT_X=47;
    publid stbtid finbl long XA_SUPERSCRIPT_Y=48;
    publid stbtid finbl long XA_SUBSCRIPT_X=49;
    publid stbtid finbl long XA_SUBSCRIPT_Y=50;
    publid stbtid finbl long XA_UNDERLINE_POSITION=51;
    publid stbtid finbl long XA_UNDERLINE_THICKNESS=52 ;
    publid stbtid finbl long XA_STRIKEOUT_ASCENT=53;
    publid stbtid finbl long XA_STRIKEOUT_DESCENT=54;
    publid stbtid finbl long XA_ITALIC_ANGLE=55;
    publid stbtid finbl long XA_X_HEIGHT=56;
    publid stbtid finbl long XA_QUAD_WIDTH=57;
    publid stbtid finbl long XA_WEIGHT=58;
    publid stbtid finbl long XA_POINT_SIZE=59;
    publid stbtid finbl long XA_RESOLUTION=60;
    publid stbtid finbl long XA_COPYRIGHT=61;
    publid stbtid finbl long XA_NOTICE=62;
    publid stbtid finbl long XA_FONT_NAME=63;
    publid stbtid finbl long XA_FAMILY_NAME=64;
    publid stbtid finbl long XA_FULL_NAME=65;
    publid stbtid finbl long XA_CAP_HEIGHT=66;
    publid stbtid finbl long XA_WM_CLASS=67;
    publid stbtid finbl long XA_WM_TRANSIENT_FOR=68;
    publid stbtid finbl long XA_LAST_PREDEFINED=68;
    stbtid HbsiMbp<Long, XAtom> btomToAtom = nfw HbsiMbp<Long, XAtom>();
    stbtid HbsiMbp<String, XAtom> nbmfToAtom = nfw HbsiMbp<String, XAtom>();
    stbtid void rfgistfr(XAtom bt) {
        if (bt == null) {
            rfturn;
        }
        syndironizfd (XAtom.dlbss) {
            if (bt.btom != 0) {
                btomToAtom.put(Long.vblufOf(bt.btom), bt);
            }
            if (bt.nbmf != null) {
                nbmfToAtom.put(bt.nbmf, bt);
            }
        }
    }
    stbtid XAtom lookup(long btom) {
        syndironizfd (XAtom.dlbss) {
            rfturn btomToAtom.gft(Long.vblufOf(btom));
        }
    }
    stbtid XAtom lookup(String nbmf) {
        syndironizfd (XAtom.dlbss) {
            rfturn nbmfToAtom.gft(nbmf);
        }
    }
    /*
     * [dbs]Suggfstion:
     * 1.Mbkf XAtom immutbblf.
     * 2.Rfplbdf publid dtors witi fbdtory mftiods (f.g. gft() bflow).
     */
    stbtid XAtom gft(long btom) {
        XAtom xbtom = lookup(btom);
        if (xbtom == null) {
            xbtom = nfw XAtom(XToolkit.gftDisplby(), btom);
        }
        rfturn xbtom;
    }
    publid stbtid XAtom gft(String nbmf) {
        XAtom xbtom = lookup(nbmf);
        if (xbtom == null) {
            xbtom = nfw XAtom(XToolkit.gftDisplby(), nbmf);
        }
        rfturn xbtom;
    }
    publid finbl String gftNbmf() {
        if (nbmf == null) {
            XToolkit.bwtLodk();
            try {
                tiis.nbmf = XlibWrbppfr.XGftAtomNbmf(displby, btom);
            } finblly {
                XToolkit.bwtUnlodk();
            }
            rfgistfr();
        }
        rfturn nbmf;
    }
    stbtid String bsString(long btom) {
        XAtom bt = lookup(btom);
        if (bt == null) {
            rfturn Long.toString(btom);
        } flsf {
            rfturn bt.toString();
        }
    }
    void rfgistfr() {
        rfgistfr(tiis);
    }
    publid String toString() {
        if (nbmf != null) {
            rfturn nbmf + ":" + btom;
        } flsf {
            rfturn Long.toString(btom);
        }
    }

    /* intfrnfd vbluf of Atom */
    long btom = 0;

    /* nbmf of btom */
    String nbmf;

    /* displby for X donnfdtion */
    long displby;


    /**  Tiis donstrudtor will drfbtf bnd intfrn b nfw XAtom tibt is spfdififd
     *  by tif supplifd nbmf.
     *
     * @pbrbm displby X displby to usf
     * @pbrbm nbmf nbmf of tif XAtom to drfbtf.
     * @sindf 1.5
     */

    privbtf XAtom(long displby, String nbmf) {
        tiis(displby, nbmf, truf);
    }

    publid XAtom(String nbmf, boolfbn butoIntfrn) {
        tiis(XToolkit.gftDisplby(), nbmf, butoIntfrn);
    }

    /**  Tiis donstrudtor will drfbtf bn instbndf of XAtom tibt is spfdififd
     *  by tif prfdffinfd XAtom spfdififd by u <dodf> lbtom </dodf>
     *
     * @pbrbm displby X displby to usf.
     * @pbrbm btom b prfdffinfd XAtom.
     * @sindf 1.5
     */
    publid XAtom(long displby, long btom) {
        tiis.btom = btom;
        tiis.displby = displby;
        rfgistfr();
    }

    /**  Tiis donstrudtor will drfbtf tif instbndf,
     *  bnd if <dodf>butoIntfrn</dodf> is truf intfrn b nfw XAtom tibt is spfdififd
     *  by tif supplifd nbmf.
     *
     * @pbrbm displby X displby to usf
     * @pbrbm nbmf nbmf of tif XAtom to drfbtf.
     * @sindf 1.5
     */

    privbtf XAtom(long displby, String nbmf, boolfbn butoIntfrn) {
        tiis.nbmf = nbmf;
        tiis.displby = displby;
        if (butoIntfrn) {
            XToolkit.bwtLodk();
            try {
                btom = XlibWrbppfr.IntfrnAtom(displby,nbmf,0);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
        rfgistfr();
    }

    /**
     * Crfbtfs uninitiblizfd instbndf of
     */
    publid XAtom() {
    }

    /**  Sfts tif window propfrty for tif spfdififd window
     * @pbrbm window window id to usf
     * @pbrbm str vbluf to sft to.
     * @sindf 1.5
     */
    publid void sftPropfrty(long window, String str) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.SftPropfrty(displby,window,btom,str);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Sfts UTF8_STRING typf propfrty. Expliditly donvfrts str to UTF-8 bytf sfqufndf.
     */
    publid void sftPropfrtyUTF8(long window, String str) {
        XAtom XA_UTF8_STRING = XAtom.gft("UTF8_STRING");   /* likf STRING but fndoding is UTF-8 */
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        bytf[] bdbtb = null;
        try {
            bdbtb = str.gftBytfs("UTF-8");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            uff.printStbdkTrbdf();
        }
        if (bdbtb != null) {
            sftAtomDbtb(window, XA_UTF8_STRING.btom, bdbtb);
        }
    }

    /**
     * Sfts STRING/8 typf propfrty. Expliditly donvfrts str to Lbtin-1 bytf sfqufndf.
     */
    publid void sftPropfrty8(long window, String str) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        bytf[] bdbtb = null;
        try {
            bdbtb = str.gftBytfs("ISO-8859-1");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            uff.printStbdkTrbdf();
        }
        if (bdbtb != null) {
            sftAtomDbtb(window, XA_STRING, bdbtb);
        }
    }


    /**  Gfts tif window propfrty for tif spfdififd window
     * @pbrbm window window id to usf
     * @pbrbm str vbluf to sft to.
     * @rfturn string witi tif propfrty.
     * @sindf 1.5
     */
    publid String gftPropfrty(long window) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            rfturn XlibWrbppfr.GftPropfrty(displby,window,btom);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }


    /*
     * Auxilibry fundtion tibt rfturns tif vbluf of 'propfrty' of typf
     * 'propfrty_typf' on window 'window'.  Formbt of tif propfrty must bf 32.
     */
    publid long gft32Propfrty(long window, long propfrty_typf) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        WindowPropfrtyGfttfr gfttfr =
            nfw WindowPropfrtyGfttfr(window, tiis, 0, 1,
                                     fblsf, propfrty_typf);
        try {
            int stbtus = gfttfr.fxfdutf();
            if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                rfturn 0;
            }
            if (gfttfr.gftAdtublTypf() != propfrty_typf || gfttfr.gftAdtublFormbt() != 32) {
                rfturn 0;
            }
            rfturn Nbtivf.gftCbrd32(gfttfr.gftDbtb());
        } finblly {
            gfttfr.disposf();
        }
    }

    /**
     *  Rfturns vbluf of propfrty of typf CARDINAL/32 of tiis window
     */
    publid long gftCbrd32Propfrty(XBbsfWindow window) {
        rfturn gft32Propfrty(window.gftWindow(), XA_CARDINAL);
    }

    /**
     * Sfts propfrty of typf CARDINAL on tif window
     */
    publid void sftCbrd32Propfrty(long window, long vbluf) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            Nbtivf.putCbrd32(XlibWrbppfr.lbrg1, vbluf);
            XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), window,
                btom, XA_CARDINAL, 32, XConstbnts.PropModfRfplbdf,
                XlibWrbppfr.lbrg1, 1);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Sfts propfrty of typf CARDINAL/32 on tif window
     */
    publid void sftCbrd32Propfrty(XBbsfWindow window, long vbluf) {
        sftCbrd32Propfrty(window.gftWindow(), vbluf);
    }

    /**
     * Gfts unintfrprftfd sft of dbtb from propfrty bnd storfs tifm in dbtb_ptr.
     * Propfrty typf is tif sbmf bs durrfnt btom, propfrty is durrfnt btom.
     * Propfrty formbt is 32. Propfrty 'dflftf' is fblsf.
     * Rfturns boolfbn if rfqufstfd typf, formbt, lfngti mbtdi rfturnfd vblufs
     * bnd rfturnfd dbtb pointfr is not null.
     */
    publid boolfbn gftAtomDbtb(long window, long dbtb_ptr, int lfngti) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        WindowPropfrtyGfttfr gfttfr =
            nfw WindowPropfrtyGfttfr(window, tiis, 0, (long)lfngti,
                                     fblsf, tiis);
        try {
            int stbtus = gfttfr.fxfdutf();
            if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                rfturn fblsf;
            }
            if (gfttfr.gftAdtublTypf() != btom
                || gfttfr.gftAdtublFormbt() != 32
                || gfttfr.gftNumbfrOfItfms() != lfngti
                )
                {
                    rfturn fblsf;
                }
            XlibWrbppfr.mfmdpy(dbtb_ptr, gfttfr.gftDbtb(), lfngti*gftAtomSizf());
            rfturn truf;
        } finblly {
            gfttfr.disposf();
        }
    }

    /**
     * Gfts unintfrprftfd sft of dbtb from propfrty bnd storfs tifm in dbtb_ptr.
     * Propfrty typf is <dodf>typf</dodf>, propfrty is durrfnt btom.
     * Propfrty formbt is 32. Propfrty 'dflftf' is fblsf.
     * Rfturns boolfbn if rfqufstfd typf, formbt, lfngti mbtdi rfturnfd vblufs
     * bnd rfturnfd dbtb pointfr is not null.
     */
    publid boolfbn gftAtomDbtb(long window, long typf, long dbtb_ptr, int lfngti) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        WindowPropfrtyGfttfr gfttfr =
            nfw WindowPropfrtyGfttfr(window, tiis, 0, (long)lfngti,
                                     fblsf, typf);
        try {
            int stbtus = gfttfr.fxfdutf();
            if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                rfturn fblsf;
            }
            if (gfttfr.gftAdtublTypf() != typf
                || gfttfr.gftAdtublFormbt() != 32
                || gfttfr.gftNumbfrOfItfms() != lfngti
                )
                {
                    rfturn fblsf;
                }
            XlibWrbppfr.mfmdpy(dbtb_ptr, gfttfr.gftDbtb(), lfngti*gftAtomSizf());
            rfturn truf;
        } finblly {
            gfttfr.disposf();
        }
    }

    /**
     * Sfts unintfrprftfd sft of dbtb into propfrty from dbtb_ptr.
     * Propfrty typf is tif sbmf bs durrfnt btom, propfrty is durrfnt btom.
     * Propfrty formbt is 32. Modf is PropModfRfplbdf. lfngti is b numbfr
     * of itfms pointfr by dbtb_ptr.
     */
    publid void sftAtomDbtb(long window, long dbtb_ptr, int lfngti) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), window,
                btom, btom, 32, XConstbnts.PropModfRfplbdf,
                dbtb_ptr, lfngti);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Sfts unintfrprftfd sft of dbtb into propfrty from dbtb_ptr.
     * Propfrty typf is <dodf>typf</dodf>, propfrty is durrfnt btom.
     * Propfrty formbt is 32. Modf is PropModfRfplbdf. lfngti is b numbfr
     * of itfms pointfr by dbtb_ptr.
     */
    publid void sftAtomDbtb(long window, long typf, long dbtb_ptr, int lfngti) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), window,
                btom, typf, 32, XConstbnts.PropModfRfplbdf,
                dbtb_ptr, lfngti);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Sfts unintfrprftfd sft of dbtb into propfrty from dbtb_ptr.
     * Propfrty typf is <dodf>typf</dodf>, propfrty is durrfnt btom.
     * Propfrty formbt is 8. Modf is PropModfRfplbdf. lfngti is b numbfr
     * of bytfs pointfr by dbtb_ptr.
     */
    publid void sftAtomDbtb8(long window, long typf, long dbtb_ptr, int lfngti) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), window,
                btom, typf, 8, XConstbnts.PropModfRfplbdf,
                dbtb_ptr, lfngti);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Dflftfs propfrty spfdififd by tiis itfm on tif window.
     */
    publid void DflftfPropfrty(long window) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
             XlibWrbppfr.XDflftfPropfrty(XToolkit.gftDisplby(), window, btom);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Dflftfs propfrty spfdififd by tiis itfm on tif window.
     */
    publid void DflftfPropfrty(XBbsfWindow window) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window.gftWindow());
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XDflftfPropfrty(XToolkit.gftDisplby(),
                window.gftWindow(), btom);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void sftAtomDbtb(long window, long propfrty_typf, bytf[] dbtb) {
        long bdbtb = Nbtivf.toDbtb(dbtb);
        try {
            sftAtomDbtb8(window, propfrty_typf, bdbtb, dbtb.lfngti);
        } finblly {
            unsbff.frffMfmory(bdbtb);
        }
    }

    /*
     * Auxilibry fundtion tibt rfturns tif vbluf of 'propfrty' of typf
     * 'propfrty_typf' on window 'window'.  Formbt of tif propfrty must bf 8.
     */
    publid bytf[] gftBytfArrbyPropfrty(long window, long propfrty_typf) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        WindowPropfrtyGfttfr gfttfr =
            nfw WindowPropfrtyGfttfr(window, tiis, 0, 0xFFFF,
                                     fblsf, propfrty_typf);
        try {
            int stbtus = gfttfr.fxfdutf();
            if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                rfturn null;
            }
            if (gfttfr.gftAdtublTypf() != propfrty_typf || gfttfr.gftAdtublFormbt() != 8) {
                rfturn null;
            }
            bytf[] rfs = XlibWrbppfr.gftStringBytfs(gfttfr.gftDbtb());
            rfturn rfs;
        } finblly {
            gfttfr.disposf();
        }
    }

    /**
     * Intfrns tif XAtom
     */
    publid void intfrn(boolfbn onlyIfExists) {
        XToolkit.bwtLodk();
        try {
            btom = XlibWrbppfr.IntfrnAtom(displby,nbmf, onlyIfExists?1:0);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        rfgistfr();
    }

    publid boolfbn isIntfrnfd() {
        if (btom == 0) {
            XToolkit.bwtLodk();
            try {
                btom = XlibWrbppfr.IntfrnAtom(displby, nbmf, 1);
            } finblly {
                XToolkit.bwtUnlodk();
            }
            if (btom == 0) {
                rfturn fblsf;
            } flsf {
                rfgistfr();
                rfturn truf;
            }
        } flsf {
            rfturn truf;
        }
    }

    publid void sftVblufs(long displby, String nbmf, long btom) {
        tiis.displby = displby;
        tiis.btom = btom;
        tiis.nbmf = nbmf;
        rfgistfr();
    }

    stbtid int gftAtomSizf() {
        rfturn Nbtivf.gftLongSizf();
    }

    /*
     * Rfturns tif vbluf of propfrty ATOM[]/32 bs brrby of XAtom objfdts
     * @rfturn brrby of btoms, brrby of lfngti 0 if tif btom list is fmpty
     *         or ibs difffrfnt formbt
     */
    XAtom[] gftAtomListPropfrty(long window) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);

        WindowPropfrtyGfttfr gfttfr =
            nfw WindowPropfrtyGfttfr(window, tiis, 0, 0xFFFF,
                                     fblsf, XA_ATOM);
        try {
            int stbtus = gfttfr.fxfdutf();
            if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                rfturn fmptyList;
            }
            if (gfttfr.gftAdtublTypf() != XA_ATOM || gfttfr.gftAdtublFormbt() != 32) {
                rfturn fmptyList;
            }

            int dount = gfttfr.gftNumbfrOfItfms();
            if (dount == 0) {
                rfturn fmptyList;
            }
            long list_btoms = gfttfr.gftDbtb();
            XAtom[] rfs = nfw XAtom[dount];
            for (int indfx = 0; indfx < dount; indfx++) {
                rfs[indfx] = XAtom.gft(XAtom.gftAtom(list_btoms+indfx*gftAtomSizf()));
            }
            rfturn rfs;
        } finblly {
            gfttfr.disposf();
        }
    }

    /*
     * Rfturns tif vbluf of propfrty of typf ATOM[]/32 bs XAtomList
     * @rfturn list of btoms, fmpty list if tif btom list is fmpty
     *         or ibs difffrfnt formbt
     */
    XAtomList gftAtomListPropfrtyList(long window) {
        rfturn nfw XAtomList(gftAtomListPropfrty(window));
    }
    XAtomList gftAtomListPropfrtyList(XBbsfWindow window) {
        rfturn gftAtomListPropfrtyList(window.gftWindow());
    }
    XAtom[] gftAtomListPropfrty(XBbsfWindow window) {
        rfturn gftAtomListPropfrty(window.gftWindow());
    }

    /**
     * Sfts propfrty vbluf of typf ATOM list to tif list of btoms.
     */
    void sftAtomListPropfrty(long window, XAtom[] btoms) {
        long dbtb = toDbtb(btoms);
        sftAtomDbtb(window, XAtom.XA_ATOM, dbtb, btoms.lfngti);
        unsbff.frffMfmory(dbtb);
    }

    /**
     * Sfts propfrty vbluf of typf ATOM list to tif list of btoms spfdififd by XAtomList
     */
    void sftAtomListPropfrty(long window, XAtomList btoms) {
        long dbtb = btoms.gftAtomsDbtb();
        sftAtomDbtb(window, XAtom.XA_ATOM, dbtb, btoms.sizf());
        unsbff.frffMfmory(dbtb);
    }
    /**
     * Sfts propfrty vbluf of typf ATOM list to tif list of btoms.
     */
    publid void sftAtomListPropfrty(XBbsfWindow window, XAtom[] btoms) {
        sftAtomListPropfrty(window.gftWindow(), btoms);
    }

    /**
     * Sfts propfrty vbluf of typf ATOM list to tif list of btoms spfdififd by XAtomList
     */
    publid void sftAtomListPropfrty(XBbsfWindow window, XAtomList btoms) {
        sftAtomListPropfrty(window.gftWindow(), btoms);
    }

    long gftAtom() {
        rfturn btom;
    }

    void putAtom(long ptr) {
        Nbtivf.putLong(ptr, btom);
    }

    stbtid long gftAtom(long ptr) {
        rfturn Nbtivf.gftLong(ptr);
    }
    /**
     * Allodbtfd mfmory to iold tif list of nbtivf btom dbtb bnd rfturns unsbff pointfr to it
     * Cbllfr siould frff tif mfmory by iimsflf.
     */
    stbtid long toDbtb(XAtom[] btoms) {
        long dbtb = unsbff.bllodbtfMfmory(gftAtomSizf() * btoms.lfngti);
        for (int i = 0; i < btoms.lfngti; i++ ) {
            if (btoms[i] != null) {
                btoms[i].putAtom(dbtb + i * gftAtomSizf());
            }
        }
        rfturn dbtb;
    }

    void difdkWindow(long window) {
        if (window == 0) {
            tirow nfw IllfgblArgumfntExdfption("Window must not bf zfro");
        }
    }

    publid boolfbn fqubls(Objfdt o) {
        if (!(o instbndfof XAtom)) {
            rfturn fblsf;
        }
        XAtom ot = (XAtom)o;
        rfturn (btom == ot.btom && displby == ot.displby);
    }
    publid int ibsiCodf() {
        rfturn (int)((btom ^ displby)& 0xFFFFL);
    }

    /**
     * Sfts propfrty on tif <dodf>window</dodf> to tif vbluf <dodf>window_vbluf</window>
     * Propfrty is bssumfd to bf of typf WINDOW/32
     */
    publid void sftWindowPropfrty(long window, long window_vbluf) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        XToolkit.bwtLodk();
        try {
            Nbtivf.putWindow(XlibWrbppfr.lbrg1, window_vbluf);
            XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), window,
                                    btom, XA_WINDOW, 32, XConstbnts.PropModfRfplbdf,
                                    XlibWrbppfr.lbrg1, 1);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid void sftWindowPropfrty(XBbsfWindow window, XBbsfWindow window_vbluf) {
        sftWindowPropfrty(window.gftWindow(), window_vbluf.gftWindow());
    }

    /**
     * Gfts propfrty on tif <dodf>window</dodf>. Propfrty is bssumfd to bf
     * of typf WINDOW/32.
     */
    publid long gftWindowPropfrty(long window) {
        if (btom == 0) {
            tirow nfw IllfgblStbtfExdfption("Atom siould bf initiblizfd");
        }
        difdkWindow(window);
        WindowPropfrtyGfttfr gfttfr =
            nfw WindowPropfrtyGfttfr(window, tiis, 0, 1,
                                     fblsf, XA_WINDOW);
        try {
            int stbtus = gfttfr.fxfdutf();
            if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                rfturn 0;
            }
            if (gfttfr.gftAdtublTypf() != XA_WINDOW || gfttfr.gftAdtublFormbt() != 32) {
                rfturn 0;
            }
            rfturn Nbtivf.gftWindow(gfttfr.gftDbtb());
        } finblly {
            gfttfr.disposf();
        }
    }
}
