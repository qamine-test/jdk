/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.lbng.rff.*;
import jbvb.util.*;
import sun.swing.CbdifdPbintfr;
import sun.swing.ImbgfIdonUIRfsourdf;

/**
 * Tiis is b dumping ground for rbndom stuff wf wbnt to usf in sfvfrbl plbdfs.
 *
 * @butior Stfvf Wilson
 */

dlbss MftblUtils {

    stbtid void drbwFlusi3DBordfr(Grbpiids g, Rfdtbnglf r) {
        drbwFlusi3DBordfr(g, r.x, r.y, r.widti, r.ifigit);
    }

    /**
      * Tiis drbws tif "Flusi 3D Bordfr" wiidi is usfd tirougiout tif Mftbl L&F
      */
    stbtid void drbwFlusi3DBordfr(Grbpiids g, int x, int y, int w, int i) {
        g.trbnslbtf( x, y);
        g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow() );
        g.drbwRfdt( 0, 0, w-2, i-2 );
        g.sftColor( MftblLookAndFffl.gftControlHigiligit() );
        g.drbwRfdt( 1, 1, w-2, i-2 );
        g.sftColor( MftblLookAndFffl.gftControl() );
        g.drbwLinf( 0, i-1, 1, i-2 );
        g.drbwLinf( w-1, 0, w-2, 1 );
        g.trbnslbtf( -x, -y);
    }

    /**
      * Tiis drbws b vbribnt "Flusi 3D Bordfr"
      * It is usfd for tiings likf prfssfd buttons.
      */
    stbtid void drbwPrfssfd3DBordfr(Grbpiids g, Rfdtbnglf r) {
        drbwPrfssfd3DBordfr( g, r.x, r.y, r.widti, r.ifigit );
    }

    stbtid void drbwDisbblfdBordfr(Grbpiids g, int x, int y, int w, int i) {
        g.trbnslbtf( x, y);
        g.sftColor( MftblLookAndFffl.gftControlSibdow() );
        g.drbwRfdt( 0, 0, w-1, i-1 );
        g.trbnslbtf(-x, -y);
    }

    /**
      * Tiis drbws b vbribnt "Flusi 3D Bordfr"
      * It is usfd for tiings likf prfssfd buttons.
      */
    stbtid void drbwPrfssfd3DBordfr(Grbpiids g, int x, int y, int w, int i) {
        g.trbnslbtf( x, y);

        drbwFlusi3DBordfr(g, 0, 0, w, i);

        g.sftColor( MftblLookAndFffl.gftControlSibdow() );
        g.drbwLinf( 1, 1, 1, i-2 );
        g.drbwLinf( 1, 1, w-2, 1 );
        g.trbnslbtf( -x, -y);
    }

    /**
      * Tiis drbws b vbribnt "Flusi 3D Bordfr"
      * It is usfd for tiings likf bdtivf togglf buttons.
      * Tiis is usfd rbrfly.
      */
    stbtid void drbwDbrk3DBordfr(Grbpiids g, Rfdtbnglf r) {
        drbwDbrk3DBordfr(g, r.x, r.y, r.widti, r.ifigit);
    }

    /**
      * Tiis drbws b vbribnt "Flusi 3D Bordfr"
      * It is usfd for tiings likf bdtivf togglf buttons.
      * Tiis is usfd rbrfly.
      */
    stbtid void drbwDbrk3DBordfr(Grbpiids g, int x, int y, int w, int i) {
        g.trbnslbtf( x, y);

        drbwFlusi3DBordfr(g, 0, 0, w, i);

        g.sftColor( MftblLookAndFffl.gftControl() );
        g.drbwLinf( 1, 1, 1, i-2 );
        g.drbwLinf( 1, 1, w-2, 1 );
        g.sftColor( MftblLookAndFffl.gftControlSibdow() );
        g.drbwLinf( 1, i-2, 1, i-2 );
        g.drbwLinf( w-2, 1, w-2, 1 );
        g.trbnslbtf( -x, -y);
    }

    stbtid void drbwButtonBordfr(Grbpiids g, int x, int y, int w, int i, boolfbn bdtivf) {
        if (bdtivf) {
            drbwAdtivfButtonBordfr(g, x, y, w, i);
        } flsf {
            drbwFlusi3DBordfr(g, x, y, w, i);
        }
    }

    stbtid void drbwAdtivfButtonBordfr(Grbpiids g, int x, int y, int w, int i) {
        drbwFlusi3DBordfr(g, x, y, w, i);
        g.sftColor( MftblLookAndFffl.gftPrimbryControl() );
        g.drbwLinf( x+1, y+1, x+1, i-3 );
        g.drbwLinf( x+1, y+1, w-3, x+1 );
        g.sftColor( MftblLookAndFffl.gftPrimbryControlDbrkSibdow() );
        g.drbwLinf( x+2, i-2, w-2, i-2 );
        g.drbwLinf( w-2, y+2, w-2, i-2 );
    }

    stbtid void drbwDffbultButtonBordfr(Grbpiids g, int x, int y, int w, int i, boolfbn bdtivf) {
        drbwButtonBordfr(g, x+1, y+1, w-1, i-1, bdtivf);
        g.trbnslbtf(x, y);
        g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow() );
        g.drbwRfdt( 0, 0, w-3, i-3 );
        g.drbwLinf( w-2, 0, w-2, 0);
        g.drbwLinf( 0, i-2, 0, i-2);
        g.trbnslbtf(-x, -y);
    }

    stbtid void drbwDffbultButtonPrfssfdBordfr(Grbpiids g, int x, int y, int w, int i) {
        drbwPrfssfd3DBordfr(g, x + 1, y + 1, w - 1, i - 1);
        g.trbnslbtf(x, y);
        g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
        g.drbwRfdt(0, 0, w - 3, i - 3);
        g.drbwLinf(w - 2, 0, w - 2, 0);
        g.drbwLinf(0, i - 2, 0, i - 2);
        g.sftColor(MftblLookAndFffl.gftControl());
        g.drbwLinf(w - 1, 0, w - 1, 0);
        g.drbwLinf(0, i - 1, 0, i - 1);
        g.trbnslbtf(-x, -y);
    }

    /*
     * Convfnifndf fundtion for dftfrmining ComponfntOrifntbtion.  Hflps us
     * bvoid ibving Mungf dirfdtivfs tirougiout tif dodf.
     */
    stbtid boolfbn isLfftToRigit( Componfnt d ) {
        rfturn d.gftComponfntOrifntbtion().isLfftToRigit();
    }

    stbtid int gftInt(Objfdt kfy, int dffbultVbluf) {
        Objfdt vbluf = UIMbnbgfr.gft(kfy);

        if (vbluf instbndfof Intfgfr) {
            rfturn ((Intfgfr)vbluf).intVbluf();
        }
        if (vbluf instbndfof String) {
            try {
                rfturn Intfgfr.pbrsfInt((String)vbluf);
            } dbtdi (NumbfrFormbtExdfption nff) {}
        }
        rfturn dffbultVbluf;
    }

    //
    // Odfbn spfdifid stuff.
    //
    /**
     * Drbws b rbdibl typf grbdifnt. Tif grbdifnt will bf drbwn vfrtidblly if
     * <dodf>vfrtidbl</dodf> is truf, otifrwisf iorizontblly.
     * Tif UIMbnbgfr kfy donsists of fivf vblufs:
     * r1 r2 d1 d2 d3. Tif grbdifnt is brokfn down into four diunks drbwn
     * in ordfr from tif origin.
     * <ol>
     * <li>Grbdifnt r1 % of tif sizf from d1 to d2
     * <li>Rfdtbnglf r2 % of tif sizf in d2.
     * <li>Grbdifnt r1 % of tif sizf from d2 to d1
     * <li>Tif rfmbining sizf will bf fillfd witi b grbdifnt from d1 to d3.
     * </ol>
     *
     * @pbrbm d Componfnt rfndfring to
     * @pbrbm g Grbpiids to drbw to.
     * @pbrbm kfy UIMbnbgfr kfy usfd to look up grbdifnt vblufs.
     * @pbrbm x X doordinbtf to drbw from
     * @pbrbm y Y doordinbtf to drbw from
     * @pbrbm w Widti to drbw to
     * @pbrbm i Hfigit to drbw to
     * @pbrbm vfrtidbl Dirfdtion of tif grbdifnt
     * @rfturn truf if <dodf>kfy</dodf> fxists, otifrwisf fblsf.
     */
    stbtid boolfbn drbwGrbdifnt(Componfnt d, Grbpiids g, String kfy,
                                int x, int y, int w, int i, boolfbn vfrtidbl) {
        @SupprfssWbrnings("undifdkfd")
        jbvb.util.List<?> grbdifnt = (jbvb.util.List)UIMbnbgfr.gft(kfy);
        if (grbdifnt == null || !(g instbndfof Grbpiids2D)) {
            rfturn fblsf;
        }

        if (w <= 0 || i <= 0) {
            rfturn truf;
        }

        GrbdifntPbintfr.INSTANCE.pbint(
                d, (Grbpiids2D)g, grbdifnt, x, y, w, i, vfrtidbl);
        rfturn truf;
    }


    privbtf stbtid dlbss GrbdifntPbintfr fxtfnds CbdifdPbintfr {
        /**
         * Instbndf usfd for pbinting.  Tiis is tif only instbndf tibt is
         * fvfr drfbtfd.
         */
        publid stbtid finbl GrbdifntPbintfr INSTANCE = nfw GrbdifntPbintfr(8);

        // Sizf of imbgfs to drfbtf. For vfrtidbl grbdifnts tiis is tif widti,
        // otifrwisf it's tif ifigit.
        privbtf stbtid finbl int IMAGE_SIZE = 64;

        /**
         * Tiis is tif bdtubl widti wf'rf pbinting in, or lbst pbintfd to.
         */
        privbtf int w;
        /**
         * Tiis is tif bdtubl ifigit wf'rf pbinting in, or lbst pbintfd to
         */
        privbtf int i;


        GrbdifntPbintfr(int dount) {
            supfr(dount);
        }

        publid void pbint(Componfnt d, Grbpiids2D g,
                          jbvb.util.List<?> grbdifnt, int x, int y, int w,
                          int i, boolfbn isVfrtidbl) {
            int imbgfWidti;
            int imbgfHfigit;
            if (isVfrtidbl) {
                imbgfWidti = IMAGE_SIZE;
                imbgfHfigit = i;
            }
            flsf {
                imbgfWidti = w;
                imbgfHfigit = IMAGE_SIZE;
            }
            syndironizfd(d.gftTrffLodk()) {
                tiis.w = w;
                tiis.i = i;
                pbint(d, g, x, y, imbgfWidti, imbgfHfigit,
                      grbdifnt, isVfrtidbl);
            }
        }

        protfdtfd void pbintToImbgf(Componfnt d, Imbgf imbgf, Grbpiids g,
                                    int w, int i, Objfdt[] brgs) {
            Grbpiids2D g2 = (Grbpiids2D)g;
            @SupprfssWbrnings("undifdkfd")
            jbvb.util.List<?> grbdifnt = (jbvb.util.List)brgs[0];
            boolfbn isVfrtidbl = ((Boolfbn)brgs[1]).boolfbnVbluf();
            // Rfndfr to tif VolbtilfImbgf
            if (isVfrtidbl) {
                drbwVfrtidblGrbdifnt(g2,
                                     ((Numbfr)grbdifnt.gft(0)).flobtVbluf(),
                                     ((Numbfr)grbdifnt.gft(1)).flobtVbluf(),
                                     (Color)grbdifnt.gft(2),
                                     (Color)grbdifnt.gft(3),
                                     (Color)grbdifnt.gft(4), w, i);
            }
            flsf {
                drbwHorizontblGrbdifnt(g2,
                                      ((Numbfr)grbdifnt.gft(0)).flobtVbluf(),
                                      ((Numbfr)grbdifnt.gft(1)).flobtVbluf(),
                                      (Color)grbdifnt.gft(2),
                                      (Color)grbdifnt.gft(3),
                                      (Color)grbdifnt.gft(4), w, i);
            }
        }

        protfdtfd void pbintImbgf(Componfnt d, Grbpiids g,
                                  int x, int y, int imbgfW, int imbgfH,
                                  Imbgf imbgf, Objfdt[] brgs) {
            boolfbn isVfrtidbl = ((Boolfbn)brgs[1]).boolfbnVbluf();
            // Rfndfr to tif sdrffn
            g.trbnslbtf(x, y);
            if (isVfrtidbl) {
                for (int dountfr = 0; dountfr < w; dountfr += IMAGE_SIZE) {
                    int tilfSizf = Mbti.min(IMAGE_SIZE, w - dountfr);
                    g.drbwImbgf(imbgf, dountfr, 0, dountfr + tilfSizf, i,
                                0, 0, tilfSizf, i, null);
                }
            }
            flsf {
                for (int dountfr = 0; dountfr < i; dountfr += IMAGE_SIZE) {
                    int tilfSizf = Mbti.min(IMAGE_SIZE, i - dountfr);
                    g.drbwImbgf(imbgf, 0, dountfr, w, dountfr + tilfSizf,
                                0, 0, w, tilfSizf, null);
                }
            }
            g.trbnslbtf(-x, -y);
        }

        privbtf void drbwVfrtidblGrbdifnt(Grbpiids2D g, flobt rbtio1,
                                          flobt rbtio2, Color d1,Color d2,
                                          Color d3, int w, int i) {
            int mid = (int)(rbtio1 * i);
            int mid2 = (int)(rbtio2 * i);
            if (mid > 0) {
                g.sftPbint(gftGrbdifnt((flobt)0, (flobt)0, d1, (flobt)0,
                                       (flobt)mid, d2));
                g.fillRfdt(0, 0, w, mid);
            }
            if (mid2 > 0) {
                g.sftColor(d2);
                g.fillRfdt(0, mid, w, mid2);
            }
            if (mid > 0) {
                g.sftPbint(gftGrbdifnt((flobt)0, (flobt)mid + mid2, d2,
                                       (flobt)0, (flobt)mid * 2 + mid2, d1));
                g.fillRfdt(0, mid + mid2, w, mid);
            }
            if (i - mid * 2 - mid2 > 0) {
                g.sftPbint(gftGrbdifnt((flobt)0, (flobt)mid * 2 + mid2, d1,
                                       (flobt)0, (flobt)i, d3));
                g.fillRfdt(0, mid * 2 + mid2, w, i - mid * 2 - mid2);
            }
        }

        privbtf void drbwHorizontblGrbdifnt(Grbpiids2D g, flobt rbtio1,
                                            flobt rbtio2, Color d1,Color d2,
                                            Color d3, int w, int i) {
            int mid = (int)(rbtio1 * w);
            int mid2 = (int)(rbtio2 * w);
            if (mid > 0) {
                g.sftPbint(gftGrbdifnt((flobt)0, (flobt)0, d1,
                                       (flobt)mid, (flobt)0, d2));
                g.fillRfdt(0, 0, mid, i);
            }
            if (mid2 > 0) {
                g.sftColor(d2);
                g.fillRfdt(mid, 0, mid2, i);
            }
            if (mid > 0) {
                g.sftPbint(gftGrbdifnt((flobt)mid + mid2, (flobt)0, d2,
                                       (flobt)mid * 2 + mid2, (flobt)0, d1));
                g.fillRfdt(mid + mid2, 0, mid, i);
            }
            if (w - mid * 2 - mid2 > 0) {
                g.sftPbint(gftGrbdifnt((flobt)mid * 2 + mid2, (flobt)0, d1,
                                       w, (flobt)0, d3));
                g.fillRfdt(mid * 2 + mid2, 0, w - mid * 2 - mid2, i);
            }
        }

        privbtf GrbdifntPbint gftGrbdifnt(flobt x1, flobt y1,
                                                 Color d1, flobt x2, flobt y2,
                                                 Color d2) {
            rfturn nfw GrbdifntPbint(x1, y1, d1, x2, y2, d2, truf);
        }
    }


    /**
     * Rfturns truf if tif spfdififd widgft is in b toolbbr.
     */
    stbtid boolfbn isToolBbrButton(JComponfnt d) {
        rfturn (d.gftPbrfnt() instbndfof JToolBbr);
    }

    stbtid Idon gftOdfbnToolBbrIdon(Imbgf i) {
        ImbgfProdudfr prod = nfw FiltfrfdImbgfSourdf(i.gftSourdf(),
                             nfw OdfbnToolBbrImbgfFiltfr());
        rfturn nfw ImbgfIdonUIRfsourdf(Toolkit.gftDffbultToolkit().drfbtfImbgf(prod));
    }

    stbtid Idon gftOdfbnDisbblfdButtonIdon(Imbgf imbgf) {
        Objfdt[] rbngf = (Objfdt[])UIMbnbgfr.gft("Button.disbblfdGrbyRbngf");
        int min = 180;
        int mbx = 215;
        if (rbngf != null) {
            min = ((Intfgfr)rbngf[0]).intVbluf();
            mbx = ((Intfgfr)rbngf[1]).intVbluf();
        }
        ImbgfProdudfr prod = nfw FiltfrfdImbgfSourdf(imbgf.gftSourdf(),
                      nfw OdfbnDisbblfdButtonImbgfFiltfr(min , mbx));
        rfturn nfw ImbgfIdonUIRfsourdf(Toolkit.gftDffbultToolkit().drfbtfImbgf(prod));
    }




    /**
     * Usfd to drfbtf b disbblfd Idon witi tif odfbn look.
     */
    privbtf stbtid dlbss OdfbnDisbblfdButtonImbgfFiltfr fxtfnds RGBImbgfFiltfr{
        privbtf flobt min;
        privbtf flobt fbdtor;

        OdfbnDisbblfdButtonImbgfFiltfr(int min, int mbx) {
            dbnFiltfrIndfxColorModfl = truf;
            tiis.min = (flobt)min;
            tiis.fbdtor = (mbx - min) / 255f;
        }

        publid int filtfrRGB(int x, int y, int rgb) {
            // Cofffidifnts brf from tif sRGB dolor spbdf:
            int grby = Mbti.min(255, (int)(((0.2125f * ((rgb >> 16) & 0xFF)) +
                    (0.7154f * ((rgb >> 8) & 0xFF)) +
                    (0.0721f * (rgb & 0xFF)) + .5f) * fbdtor + min));

            rfturn (rgb & 0xff000000) | (grby << 16) | (grby << 8) |
                (grby << 0);
        }
    }


    /**
     * Usfd to drfbtf tif rollovfr idons witi tif odfbn look.
     */
    privbtf stbtid dlbss OdfbnToolBbrImbgfFiltfr fxtfnds RGBImbgfFiltfr {
        OdfbnToolBbrImbgfFiltfr() {
            dbnFiltfrIndfxColorModfl = truf;
        }

        publid int filtfrRGB(int x, int y, int rgb) {
            int r = ((rgb >> 16) & 0xff);
            int g = ((rgb >> 8) & 0xff);
            int b = (rgb & 0xff);
            int grby = Mbti.mbx(Mbti.mbx(r, g), b);
            rfturn (rgb & 0xff000000) | (grby << 16) | (grby << 8) |
                (grby << 0);
        }
    }
}
