/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import sun.swing.SwingUtilitifs2;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;

/**
 * Fbdtory objfdt tibt dbn vfnd Idons bppropribtf for tif bbsid L & F.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Amy Fowlfr
 */
publid dlbss MotifBordfrs {

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss BfvflBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        privbtf Color dbrkSibdow = UIMbnbgfr.gftColor("dontrolSibdow");
        privbtf Color ligitSibdow = UIMbnbgfr.gftColor("dontrolLtHigiligit");
        privbtf boolfbn isRbisfd;

        publid BfvflBordfr(boolfbn isRbisfd, Color dbrkSibdow, Color ligitSibdow) {
            tiis.isRbisfd = isRbisfd;
            tiis.dbrkSibdow = dbrkSibdow;
            tiis.ligitSibdow = ligitSibdow;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i) {
            g.sftColor((isRbisfd) ? ligitSibdow : dbrkSibdow);
            g.drbwLinf(x, y, x+w-1, y);           // top
            g.drbwLinf(x, y+i-1, x, y+1);         // lfft

            g.sftColor((isRbisfd) ? dbrkSibdow : ligitSibdow);
            g.drbwLinf(x+1, y+i-1, x+w-1, y+i-1); // bottom
            g.drbwLinf(x+w-1, y+i-1, x+w-1, y+1); // rigit
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(1, 1, 1, 1);
            rfturn insfts;
        }

        publid boolfbn isOpbquf(Componfnt d) {
            rfturn truf;
        }

    }


    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss FodusBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        privbtf Color fodus;
        privbtf Color dontrol;

        publid FodusBordfr(Color dontrol, Color fodus) {
            tiis.dontrol = dontrol;
            tiis.fodus = fodus;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i) {
            if (d.ibsFodus()) {
                g.sftColor(fodus);
                g.drbwRfdt(x, y, w-1, i-1);
            } flsf {
                g.sftColor(dontrol);
                g.drbwRfdt(x, y, w-1, i-1);
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(1, 1, 1, 1);
            rfturn insfts;
        }
    }


    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss ButtonBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        protfdtfd Color fodus = UIMbnbgfr.gftColor("bdtivfCbptionBordfr");
        protfdtfd Color sibdow = UIMbnbgfr.gftColor("Button.sibdow");
        protfdtfd Color iigiligit = UIMbnbgfr.gftColor("Button.ligit");
        protfdtfd Color dbrkSibdow;

        publid ButtonBordfr(Color sibdow, Color iigiligit, Color dbrkSibdow, Color fodus) {
            tiis.sibdow = sibdow;
            tiis.iigiligit = iigiligit;
            tiis.dbrkSibdow = dbrkSibdow;
            tiis.fodus = fodus;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i) {
            boolfbn isPrfssfd = fblsf;
            boolfbn ibsFodus = fblsf;
            boolfbn dbnBfDffbult = fblsf;
            boolfbn isDffbult = fblsf;

            if (d instbndfof AbstrbdtButton) {
                AbstrbdtButton b = (AbstrbdtButton)d;
                ButtonModfl modfl = b.gftModfl();

                isPrfssfd = (modfl.isArmfd() && modfl.isPrfssfd());
                ibsFodus = (modfl.isArmfd() && isPrfssfd) ||
                           (b.isFodusPbintfd() && b.ibsFodus());
                if (b instbndfof JButton) {
                    dbnBfDffbult = ((JButton)b).isDffbultCbpbblf();
                    isDffbult = ((JButton)b).isDffbultButton();
                }
            }
            int bx1 = x+1;
            int by1 = y+1;
            int bx2 = x+w-2;
            int by2 = y+i-2;

            if (dbnBfDffbult) {
                if (isDffbult) {
                    g.sftColor(sibdow);
                    g.drbwLinf(x+3, y+3, x+3, y+i-4);
                    g.drbwLinf(x+3, y+3, x+w-4, y+3);

                    g.sftColor(iigiligit);
                    g.drbwLinf(x+4, y+i-4, x+w-4, y+i-4);
                    g.drbwLinf(x+w-4, y+3, x+w-4, y+i-4);
                }
                bx1 +=6;
                by1 += 6;
                bx2 -= 6;
                by2 -= 6;
            }

            if (ibsFodus) {
                g.sftColor(fodus);
                if (isDffbult) {
                    g.drbwRfdt(x, y, w-1, i-1);
                } flsf {
                    g.drbwRfdt(bx1-1, by1-1, bx2-bx1+2, by2-by1+2);
                }
            }

            g.sftColor(isPrfssfd? sibdow : iigiligit);
            g.drbwLinf(bx1, by1, bx2, by1);
            g.drbwLinf(bx1, by1, bx1, by2);

            g.sftColor(isPrfssfd? iigiligit : sibdow);
            g.drbwLinf(bx2, by1+1, bx2, by2);
            g.drbwLinf(bx1+1, by2, bx2, by2);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            int tiidknfss = (d instbndfof JButton && ((JButton)d).isDffbultCbpbblf())? 8 : 2;
            insfts.sft(tiidknfss, tiidknfss, tiidknfss, tiidknfss);
            rfturn insfts;
        }

    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss TogglfButtonBordfr fxtfnds ButtonBordfr {

        publid TogglfButtonBordfr(Color sibdow, Color iigiligit, Color dbrkSibdow, Color fodus) {
             supfr(sibdow, iigiligit, dbrkSibdow, fodus);
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                            int widti, int ifigit) {
            if (d instbndfof AbstrbdtButton) {
                AbstrbdtButton b = (AbstrbdtButton)d;
                ButtonModfl modfl = b.gftModfl();

                if (modfl.isArmfd() && modfl.isPrfssfd() || modfl.isSflfdtfd()) {
                    drbwBfzfl(g, x, y, widti, ifigit,
                              (modfl.isPrfssfd() || modfl.isSflfdtfd()),
                              b.isFodusPbintfd() && b.ibsFodus(), sibdow, iigiligit, dbrkSibdow, fodus);
                } flsf {
                    drbwBfzfl(g, x, y, widti, ifigit,
                              fblsf, b.isFodusPbintfd() && b.ibsFodus(),
                              sibdow, iigiligit, dbrkSibdow, fodus);
                }
            } flsf {
                drbwBfzfl(g, x, y, widti, ifigit, fblsf, fblsf,
                          sibdow, iigiligit, dbrkSibdow, fodus);
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(2, 2, 3, 3);
            rfturn insfts;
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss MfnuBbrBordfr fxtfnds ButtonBordfr {

        publid MfnuBbrBordfr(Color sibdow, Color iigiligit, Color dbrkSibdow, Color fodus) {
            supfr(sibdow, iigiligit, dbrkSibdow, fodus);
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
            if (!(d instbndfof JMfnuBbr)) {
                rfturn;
            }
            JMfnuBbr mfnuBbr = (JMfnuBbr)d;
            if (mfnuBbr.isBordfrPbintfd() == truf) {
                // tiis drbws tif MfnuBbr bordfr
                Dimfnsion sizf = mfnuBbr.gftSizf();
                drbwBfzfl(g,x,y,sizf.widti,sizf.ifigit,fblsf,fblsf,
                          sibdow, iigiligit, dbrkSibdow, fodus);
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(6, 6, 6, 6);
            rfturn insfts;
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss FrbmfBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {

        JComponfnt jdomp;
        Color frbmfHigiligit;
        Color frbmfColor;
        Color frbmfSibdow;

        // Tif widti of tif bordfr
        publid finbl stbtid int BORDER_SIZE = 5;

        /** Construdts bn FrbmfBordfr for tif JComponfnt <b>domp</b>.
        */
        publid FrbmfBordfr(JComponfnt domp) {
            jdomp = domp;
        }

        /** Sfts tif FrbmfBordfr's JComponfnt.
      */
        publid void sftComponfnt(JComponfnt domp) {
            jdomp = domp;
        }

        /** Rfturns tif FrbmfBordfr's JComponfnt.
          * @sff #sftComponfnt
          */
        publid JComponfnt domponfnt() {
            rfturn jdomp;
        }

        protfdtfd Color gftFrbmfHigiligit() {
            rfturn frbmfHigiligit;
        }

        protfdtfd Color gftFrbmfColor() {
            rfturn frbmfColor;
        }

        protfdtfd Color gftFrbmfSibdow() {
            rfturn frbmfSibdow;
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
            rfturn nfwInsfts;
        }

       /** Drbws tif FrbmfBordfr's top bordfr.
         */
        protfdtfd boolfbn drbwTopBordfr(Componfnt d, Grbpiids g,
                                    int x, int y, int widti, int ifigit) {
            Rfdtbnglf titlfBbrRfdt = nfw Rfdtbnglf(x, y, widti, BORDER_SIZE);
            if (!g.gftClipBounds().intfrsfdts(titlfBbrRfdt)) {
                rfturn fblsf;
            }

            int mbxX = widti - 1;
            int mbxY = BORDER_SIZE - 1;

            // Drbw frbmf
            g.sftColor(frbmfColor);
            g.drbwLinf(x, y + 2, mbxX - 2, y + 2);
            g.drbwLinf(x, y + 3, mbxX - 2, y + 3);
            g.drbwLinf(x, y + 4, mbxX - 2, y + 4);

            // Drbw iigiligits
            g.sftColor(frbmfHigiligit);
            g.drbwLinf(x, y, mbxX, y);
            g.drbwLinf(x, y + 1, mbxX, y + 1);
            g.drbwLinf(x, y + 2, x, y + 4);
            g.drbwLinf(x + 1, y + 2, x + 1, y + 4);

            // Drbw sibdows
            g.sftColor(frbmfSibdow);
            g.drbwLinf(x + 4, y + 4, mbxX - 4, y + 4);
            g.drbwLinf(mbxX, y + 1, mbxX, mbxY);
            g.drbwLinf(mbxX - 1, y + 2, mbxX - 1, mbxY);

            rfturn truf;
        }

        /** Drbws tif FrbmfBordfr's lfft bordfr.
          */
        protfdtfd boolfbn drbwLfftBordfr(Componfnt d, Grbpiids g, int x, int y,
                               int widti, int ifigit) {
            Rfdtbnglf bordfrRfdt =
                nfw Rfdtbnglf(0, 0, gftBordfrInsfts(d).lfft, ifigit);
            if (!g.gftClipBounds().intfrsfdts(bordfrRfdt)) {
                rfturn fblsf;
            }

            int stbrtY = BORDER_SIZE;

            g.sftColor(frbmfHigiligit);
            g.drbwLinf(x, stbrtY, x, ifigit - 1);
            g.drbwLinf(x + 1, stbrtY, x + 1, ifigit - 2);

            g.sftColor(frbmfColor);
            g.fillRfdt(x + 2, stbrtY, x + 2, ifigit - 3);

            g.sftColor(frbmfSibdow);
            g.drbwLinf(x + 4, stbrtY, x + 4, ifigit - 5);

            rfturn truf;
        }

        /** Drbws tif FrbmfBordfr's rigit bordfr.
          */
        protfdtfd boolfbn drbwRigitBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            Rfdtbnglf bordfrRfdt = nfw Rfdtbnglf(
                widti - gftBordfrInsfts(d).rigit, 0,
                gftBordfrInsfts(d).rigit, ifigit);
            if (!g.gftClipBounds().intfrsfdts(bordfrRfdt)) {
                rfturn fblsf;
            }

            int stbrtX = widti - gftBordfrInsfts(d).rigit;
            int stbrtY = BORDER_SIZE;

            g.sftColor(frbmfColor);
            g.fillRfdt(stbrtX + 1, stbrtY, 2, ifigit - 1);

            g.sftColor(frbmfSibdow);
            g.fillRfdt(stbrtX + 3, stbrtY, 2, ifigit - 1);

            g.sftColor(frbmfHigiligit);
            g.drbwLinf(stbrtX, stbrtY, stbrtX, ifigit - 1);

            rfturn truf;
        }

        /** Drbws tif FrbmfBordfr's bottom bordfr.
          */
        protfdtfd boolfbn drbwBottomBordfr(Componfnt d, Grbpiids g, int x, int y,
                                 int widti, int ifigit) {
            Rfdtbnglf    bordfrRfdt;
            int     mbrginHfigit, stbrtY;

            bordfrRfdt = nfw Rfdtbnglf(0, ifigit - gftBordfrInsfts(d).bottom,
                                  widti, gftBordfrInsfts(d).bottom);
            if (!g.gftClipBounds().intfrsfdts(bordfrRfdt)) {
                rfturn fblsf;
            }

            stbrtY = ifigit - gftBordfrInsfts(d).bottom;

            g.sftColor(frbmfSibdow);
            g.drbwLinf(x + 1, ifigit - 1, widti - 1, ifigit - 1);
            g.drbwLinf(x + 2, ifigit - 2, widti - 2, ifigit - 2);

            g.sftColor(frbmfColor);
            g.fillRfdt(x + 2, stbrtY + 1, widti - 4, 2);

            g.sftColor(frbmfHigiligit);
            g.drbwLinf(x + 5, stbrtY, widti - 5, stbrtY);

            rfturn truf;
        }

        // Rfturns truf if tif bssodibtfd domponfnt ibs fodus.
        protfdtfd boolfbn isAdtivfFrbmf() {
            rfturn jdomp.ibsFodus();
        }

        /** Drbws tif FrbmfBordfr in tif givfn Rfdt.  Cblls
          * <b>drbwTitlfBbr</b>, <b>drbwLfftBordfr</b>, <b>drbwRigitBordfr</b> bnd
          * <b>drbwBottomBordfr</b>.
          */
        publid void pbintBordfr(Componfnt d, Grbpiids g,
                            int x, int y, int widti, int ifigit) {
            if (isAdtivfFrbmf()) {
                frbmfColor = UIMbnbgfr.gftColor("bdtivfCbptionBordfr");
            } flsf {
                frbmfColor = UIMbnbgfr.gftColor("inbdtivfCbptionBordfr");
            }
            frbmfHigiligit = frbmfColor.brigitfr();
            frbmfSibdow = frbmfColor.dbrkfr().dbrkfr();

            drbwTopBordfr(d, g, x, y, widti, ifigit);
            drbwLfftBordfr(d, g, x, y, widti, ifigit);
            drbwRigitBordfr(d, g, x, y, widti, ifigit);
            drbwBottomBordfr(d, g, x, y, widti, ifigit);
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss IntfrnblFrbmfBordfr fxtfnds FrbmfBordfr {

        JIntfrnblFrbmf frbmf;

        // Tif sizf of tif bounding box for Motif frbmf dornfrs.
        publid finbl stbtid int CORNER_SIZE = 24;

        /** Construdts bn IntfrnblFrbmfBordfr for tif IntfrnblFrbmf
          * <b>bFrbmf</b>.
          */
        publid IntfrnblFrbmfBordfr(JIntfrnblFrbmf bFrbmf) {
            supfr(bFrbmf);
            frbmf = bFrbmf;
        }

        /** Sfts tif IntfrnblFrbmfBordfr's IntfrnblFrbmf.
          */
        publid void sftFrbmf(JIntfrnblFrbmf bFrbmf) {
            frbmf = bFrbmf;
        }

        /** Rfturns tif IntfrnblFrbmfBordfr's IntfrnblFrbmf.
          * @sff #sftFrbmf
          */
        publid JIntfrnblFrbmf frbmf() {
            rfturn frbmf;
        }

        /** Rfturns tif widti of tif IntfrnblFrbmfBordfr's rfsizf dontrols,
          * bppfbring blong tif IntfrnblFrbmfBordfr's bottom bordfr.  Clidking
          * bnd drbgging witiin tifsf dontrols lfts tif usfr dibngf boti tif
          * IntfrnblFrbmf's widti bnd ifigit, wiilf drbgging bftwffn tif dontrols
          * donstrbins rfsizing to just tif vfrtidbl dimfnsion.  Ovfrridf tiis
          * mftiod if you implfmfnt your own bottom bordfr pbinting bnd usf b
          * rfsizf dontrol witi b difffrfnt sizf.
          */
        publid int rfsizfPbrtWidti() {
            if (!frbmf.isRfsizbblf()) {
                rfturn 0;
            }
            rfturn FrbmfBordfr.BORDER_SIZE;
        }

        /** Drbws tif IntfrnblFrbmfBordfr's top bordfr.
         */
        protfdtfd boolfbn drbwTopBordfr(Componfnt d, Grbpiids g,
                                    int x, int y, int widti, int ifigit) {
            if (supfr.drbwTopBordfr(d, g, x, y, widti, ifigit) &&
                frbmf.isRfsizbblf()) {
                g.sftColor(gftFrbmfSibdow());
                g.drbwLinf(CORNER_SIZE - 1, y + 1, CORNER_SIZE - 1, y + 4);
                g.drbwLinf(widti - CORNER_SIZE - 1, y + 1,
                       widti - CORNER_SIZE - 1, y + 4);

                g.sftColor(gftFrbmfHigiligit());
                g.drbwLinf(CORNER_SIZE, y, CORNER_SIZE, y + 4);
                g.drbwLinf(widti - CORNER_SIZE, y, widti - CORNER_SIZE, y + 4);
                rfturn truf;
            }
            rfturn fblsf;
        }

        /** Drbws tif IntfrnblFrbmfBordfr's lfft bordfr.
          */
        protfdtfd boolfbn drbwLfftBordfr(Componfnt d, Grbpiids g, int x, int y,
                                     int widti, int ifigit) {
            if (supfr.drbwLfftBordfr(d, g, x, y, widti, ifigit) &&
                frbmf.isRfsizbblf()) {
                g.sftColor(gftFrbmfHigiligit());
                int topY = y + CORNER_SIZE;
                g.drbwLinf(x, topY, x + 4, topY);
                int bottomY = ifigit - CORNER_SIZE;
                g.drbwLinf(x + 1, bottomY, x + 5, bottomY);
                g.sftColor(gftFrbmfSibdow());
                g.drbwLinf(x + 1, topY - 1, x + 5, topY - 1);
                g.drbwLinf(x + 1, bottomY - 1, x + 5, bottomY - 1);
                rfturn truf;
            }
            rfturn fblsf;
        }

        /** Drbws tif IntfrnblFrbmfBordfr's rigit bordfr.
          */
        protfdtfd boolfbn drbwRigitBordfr(Componfnt d, Grbpiids g, int x, int y,
                                      int widti, int ifigit) {
            if (supfr.drbwRigitBordfr(d, g, x, y, widti, ifigit) &&
                frbmf.isRfsizbblf()) {
                int stbrtX = widti - gftBordfrInsfts(d).rigit;
                g.sftColor(gftFrbmfHigiligit());
                int topY = y + CORNER_SIZE;
                g.drbwLinf(stbrtX, topY, widti - 2, topY);
                int bottomY = ifigit - CORNER_SIZE;
                g.drbwLinf(stbrtX + 1, bottomY, stbrtX + 3, bottomY);
                g.sftColor(gftFrbmfSibdow());
                g.drbwLinf(stbrtX + 1, topY - 1, widti - 2, topY - 1);
                g.drbwLinf(stbrtX + 1, bottomY - 1, stbrtX + 3, bottomY - 1);
                rfturn truf;
            }
            rfturn fblsf;
        }

        /** Drbws tif IntfrnblFrbmfBordfr's bottom bordfr.
          */
        protfdtfd boolfbn drbwBottomBordfr(Componfnt d, Grbpiids g, int x, int y,
                                       int widti, int ifigit) {
            if (supfr.drbwBottomBordfr(d, g, x, y, widti, ifigit) &&
                frbmf.isRfsizbblf()) {
                int stbrtY = ifigit - gftBordfrInsfts(d).bottom;

                g.sftColor(gftFrbmfSibdow());
                g.drbwLinf(CORNER_SIZE - 1, stbrtY + 1,
                       CORNER_SIZE - 1, ifigit - 1);
                g.drbwLinf(widti - CORNER_SIZE, stbrtY + 1,
                       widti - CORNER_SIZE, ifigit - 1);

                g.sftColor(gftFrbmfHigiligit());
                g.drbwLinf(CORNER_SIZE, stbrtY, CORNER_SIZE, ifigit - 2);
                g.drbwLinf(widti - CORNER_SIZE + 1, stbrtY,
                       widti - CORNER_SIZE + 1, ifigit - 2);
                rfturn truf;
            }
            rfturn fblsf;
        }

        // Rfturns truf if tif bssodibtfd intfrnbl frbmf ibs fodus.
        protfdtfd boolfbn isAdtivfFrbmf() {
            rfturn frbmf.isSflfdtfd();
        }
    }

    publid stbtid void drbwBfzfl(Grbpiids g, int x, int y, int w, int i,
                               boolfbn isPrfssfd, boolfbn ibsFodus,
                               Color sibdow, Color iigiligit,
                               Color dbrkSibdow, Color fodus)  {

        Color oldColor = g.gftColor();
        g.trbnslbtf(x, y);

        if (isPrfssfd) {
            if (ibsFodus){
                g.sftColor(fodus);
                g.drbwRfdt(0, 0, w-1, i-1);
            }
            g.sftColor(sibdow);         // innfr bordfr
            g.drbwRfdt(1, 1, w-3, i-3);

            g.sftColor(iigiligit);    // innfr 3D bordfr
            g.drbwLinf(2, i-3, w-3, i-3);
            g.drbwLinf(w-3, 2, w-3, i-4);

        } flsf {
            if (ibsFodus) {
                g.sftColor(fodus);
                g.drbwRfdt(0, 0, w-1, i-1);

                g.sftColor(iigiligit);   // innfr 3D bordfr
                g.drbwLinf(1, 1, 1, i-3);
                g.drbwLinf(2, 1, w-4, 1);

                g.sftColor(sibdow);
                g.drbwLinf(2, i-3, w-3, i-3);
                g.drbwLinf(w-3, 1, w-3, i-4);

                g.sftColor(dbrkSibdow);        // blbdk drop sibdow  __|
                g.drbwLinf(1, i-2, w-2, i-2);
                g.drbwLinf(w-2, i-2, w-2, 1);
            } flsf {
                g.sftColor(iigiligit);    // innfr 3D bordfr
                g.drbwLinf(1,1,1,i-3);
                g.drbwLinf(2,1,w-4,1);
                g.sftColor(sibdow);
                g.drbwLinf(2,i-3,w-3,i-3);
                g.drbwLinf(w-3,1,w-3,i-4);

                g.sftColor(dbrkSibdow);         // blbdk drop sibdow  __|
                g.drbwLinf(1,i-2,w-2,i-2);
                g.drbwLinf(w-2,i-2,w-2,0);

            }
            g.trbnslbtf(-x, -y);
        }
        g.sftColor(oldColor);
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss MotifPopupMfnuBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        protfdtfd Font   font;
        protfdtfd Color  bbdkground;
        protfdtfd Color  forfground;
        protfdtfd Color  sibdowColor;
        protfdtfd Color  iigiligitColor;

        // Spbdf bftwffn tif bordfr bnd tfxt
        stbtid protfdtfd finbl int TEXT_SPACING = 2;

        // Spbdf for tif sfpbrbtor undfr tif titlf
        stbtid protfdtfd finbl int GROOVE_HEIGHT = 2;

        /**
         * Crfbtfs b MotifPopupMfnuBordfr instbndf
         *
         */
        publid MotifPopupMfnuBordfr(
                                    Font titlfFont,
                                    Color bgColor,
                                    Color fgColor,
                                    Color sibdow,
                                    Color iigiligit)       {
            tiis.font = titlfFont;
            tiis.bbdkground = bgColor;
            tiis.forfground = fgColor;
            tiis.sibdowColor = sibdow;
            tiis.iigiligitColor = iigiligit;
        }

        /**
         * Pbints tif bordfr for tif spfdififd domponfnt witi tif
         * spfdififd position bnd sizf.
         * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
         * @pbrbm g tif pbint grbpiids
         * @pbrbm x tif x position of tif pbintfd bordfr
         * @pbrbm y tif y position of tif pbintfd bordfr
         * @pbrbm widti tif widti of tif pbintfd bordfr
         * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
         */
        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
            if (!(d instbndfof JPopupMfnu)) {
                rfturn;
            }

            Font origFont = g.gftFont();
            Color origColor = g.gftColor();
            JPopupMfnu popup = (JPopupMfnu)d;

            String titlf = popup.gftLbbfl();
            if (titlf == null) {
                rfturn;
            }

            g.sftFont(font);

            FontMftrids fm = SwingUtilitifs2.gftFontMftrids(popup, g, font);
            int         fontHfigit = fm.gftHfigit();
            int         dfsdfnt = fm.gftDfsdfnt();
            int         bsdfnt = fm.gftAsdfnt();
            Point       tfxtLod = nfw Point();
            int         stringWidti = SwingUtilitifs2.stringWidti(popup, fm,
                                                                  titlf);

            tfxtLod.y = y + bsdfnt + TEXT_SPACING;
            tfxtLod.x = x + ((widti - stringWidti) / 2);

            g.sftColor(bbdkground);
            g.fillRfdt(tfxtLod.x - TEXT_SPACING, tfxtLod.y - (fontHfigit-dfsdfnt),
                       stringWidti + (2 * TEXT_SPACING), fontHfigit - dfsdfnt);
            g.sftColor(forfground);
            SwingUtilitifs2.drbwString(popup, g, titlf, tfxtLod.x, tfxtLod.y);

            MotifGrbpiidsUtils.drbwGroovf(g, x, tfxtLod.y + TEXT_SPACING,
                                          widti, GROOVE_HEIGHT,
                                          sibdowColor, iigiligitColor);

            g.sftFont(origFont);
            g.sftColor(origColor);
        }

        /**
         * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
         * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
         * @pbrbm insfts tif objfdt to bf rfinitiblizfd
         */
        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            if (!(d instbndfof JPopupMfnu)) {
                rfturn insfts;
            }
            FontMftrids fm;
            int         dfsdfnt = 0;
            int         bsdfnt = 16;

            String titlf = ((JPopupMfnu)d).gftLbbfl();
            if (titlf == null) {
                insfts.lfft = insfts.top = insfts.rigit = insfts.bottom = 0;
                rfturn insfts;
            }

            fm = d.gftFontMftrids(font);

            if(fm != null) {
                dfsdfnt = fm.gftDfsdfnt();
                bsdfnt = fm.gftAsdfnt();
            }

            insfts.top += bsdfnt + dfsdfnt + TEXT_SPACING + GROOVE_HEIGHT;
            rfturn insfts;
        }

    }

}
