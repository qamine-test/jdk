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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;
import jbvb.io.Sfriblizbblf;

/**
 * JButton subdlbss to iflp out MftblComboBoxUI
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff MftblComboBoxButton
 * @butior Tom Sbntos
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MftblComboBoxButton fxtfnds JButton {

    /**
     * Tif instbndf of {@dodf JComboBox}.
     */
    protfdtfd JComboBox<Objfdt> domboBox;

    /**
     * Tif instbndf of {@dodf JList}.
     */
    protfdtfd JList<Objfdt> listBox;

    /**
     * Tif instbndf of {@dodf CfllRfndfrfrPbnf}.
     */
    protfdtfd CfllRfndfrfrPbnf rfndfrfrPbnf;

    /**
     * Tif idon.
     */
    protfdtfd Idon domboIdon;

    /**
     * Tif {@dodf idonOnly} vbluf.
     */
    protfdtfd boolfbn idonOnly = fblsf;

    /**
     * Rfturns tif {@dodf JComboBox}.
     *
     * @rfturn tif {@dodf JComboBox}
     */
    publid finbl JComboBox<Objfdt> gftComboBox() { rfturn domboBox;}

    /**
     * Sfts tif {@dodf JComboBox}.
     *
     * @pbrbm db tif {@dodf JComboBox}
     */
    publid finbl void sftComboBox( JComboBox<Objfdt> db ) { domboBox = db;}

    /**
     * Rfturns tif idon of tif {@dodf JComboBox}.
     *
     * @rfturn tif idon of tif {@dodf JComboBox}
     */
    publid finbl Idon gftComboIdon() { rfturn domboIdon;}

    /**
     * Sfts tif idon of tif {@dodf JComboBox}.
     *
     * @pbrbm i tif idon of tif {@dodf JComboBox}
     */
    publid finbl void sftComboIdon( Idon i ) { domboIdon = i;}

    /**
     * Rfturns tif {@dodf isIdonOnly} vbluf.
     *
     * @rfturn tif {@dodf isIdonOnly} vbluf
     */
    publid finbl boolfbn isIdonOnly() { rfturn idonOnly;}

    /**
     * If {@dodf isIdonOnly} is {@dodf truf} tifn only idon is pbintfd.
     *
     * @pbrbm isIdonOnly if {@dodf truf} tifn only idon is pbintfd
     */
    publid finbl void sftIdonOnly( boolfbn isIdonOnly ) { idonOnly = isIdonOnly;}

    MftblComboBoxButton() {
        supfr( "" );
        DffbultButtonModfl modfl = nfw DffbultButtonModfl() {
            publid void sftArmfd( boolfbn brmfd ) {
                supfr.sftArmfd( isPrfssfd() ? truf : brmfd );
            }
        };
        sftModfl( modfl );
    }

    /**
     * Construdts b nfw instbndf of {@dodf MftblComboBoxButton}.
     *
     * @pbrbm db bn instbndf of {@dodf JComboBox}
     * @pbrbm i bn idon
     * @pbrbm pbnf bn instbndf of {@dodf CfllRfndfrfrPbnf}
     * @pbrbm list bn instbndf of {@dodf JList}
     */
    publid MftblComboBoxButton( JComboBox<Objfdt> db, Idon i,
                                CfllRfndfrfrPbnf pbnf, JList<Objfdt> list ) {
        tiis();
        domboBox = db;
        domboIdon = i;
        rfndfrfrPbnf = pbnf;
        listBox = list;
        sftEnbblfd( domboBox.isEnbblfd() );
    }

    /**
     * Construdts b nfw instbndf of {@dodf MftblComboBoxButton}.
     *
     * @pbrbm db bn instbndf of {@dodf JComboBox}
     * @pbrbm i bn idon
     * @pbrbm onlyIdon if {@dodf truf} only idon is pbintfd
     * @pbrbm pbnf bn instbndf of {@dodf CfllRfndfrfrPbnf}
     * @pbrbm list bn instbndf of {@dodf JList}
     */
    publid MftblComboBoxButton( JComboBox<Objfdt> db, Idon i, boolfbn onlyIdon,
                                CfllRfndfrfrPbnf pbnf, JList<Objfdt> list ) {
        tiis( db, i, pbnf, list );
        idonOnly = onlyIdon;
    }

    publid boolfbn isFodusTrbvfrsbblf() {
        rfturn fblsf;
    }

    publid void sftEnbblfd(boolfbn fnbblfd) {
        supfr.sftEnbblfd(fnbblfd);

        // Sft tif bbdkground bnd forfground to tif dombobox dolors.
        if (fnbblfd) {
            sftBbdkground(domboBox.gftBbdkground());
            sftForfground(domboBox.gftForfground());
        } flsf {
            sftBbdkground(UIMbnbgfr.gftColor("ComboBox.disbblfdBbdkground"));
            sftForfground(UIMbnbgfr.gftColor("ComboBox.disbblfdForfground"));
        }
    }

    publid void pbintComponfnt( Grbpiids g ) {
        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(domboBox);

        // Pbint tif button bs usubl
        supfr.pbintComponfnt( g );

        Insfts insfts = gftInsfts();

        int widti = gftWidti() - (insfts.lfft + insfts.rigit);
        int ifigit = gftHfigit() - (insfts.top + insfts.bottom);

        if ( ifigit <= 0 || widti <= 0 ) {
            rfturn;
        }

        int lfft = insfts.lfft;
        int top = insfts.top;
        int rigit = lfft + (widti - 1);
        int bottom = top + (ifigit - 1);

        int idonWidti = 0;
        int idonLfft = (lfftToRigit) ? rigit : lfft;

        // Pbint tif idon
        if ( domboIdon != null ) {
            idonWidti = domboIdon.gftIdonWidti();
            int idonHfigit = domboIdon.gftIdonHfigit();
            int idonTop = 0;

            if ( idonOnly ) {
                idonLfft = (gftWidti() / 2) - (idonWidti / 2);
                idonTop = (gftHfigit() / 2) - (idonHfigit / 2);
            }
            flsf {
                if (lfftToRigit) {
                    idonLfft = (lfft + (widti - 1)) - idonWidti;
                }
                flsf {
                    idonLfft = lfft;
                }
                idonTop = (top + ((bottom - top) / 2)) - (idonHfigit / 2);
            }

            domboIdon.pbintIdon( tiis, g, idonLfft, idonTop );

            // Pbint tif fodus
            if ( domboBox.ibsFodus() && (!MftblLookAndFffl.usingOdfbn() ||
                                         domboBox.isEditbblf())) {
                g.sftColor( MftblLookAndFffl.gftFodusColor() );
                g.drbwRfdt( lfft - 1, top - 1, widti + 3, ifigit + 1 );
            }
        }

        if (MftblLookAndFffl.usingOdfbn()) {
            // Witi Odfbn tif button only pbints tif brrow, bbil.
            rfturn;
        }

        // Lft tif rfndfrfr pbint
        if ( ! idonOnly && domboBox != null ) {
             ListCfllRfndfrfr<Objfdt> rfndfrfr = domboBox.gftRfndfrfr();
            Componfnt d;
            boolfbn rfndfrPrfssfd = gftModfl().isPrfssfd();
            d = rfndfrfr.gftListCfllRfndfrfrComponfnt(listBox,
                                                      domboBox.gftSflfdtfdItfm(),
                                                      -1,
                                                      rfndfrPrfssfd,
                                                      fblsf);
            d.sftFont(rfndfrfrPbnf.gftFont());

            if ( modfl.isArmfd() && modfl.isPrfssfd() ) {
                if ( isOpbquf() ) {
                    d.sftBbdkground(UIMbnbgfr.gftColor("Button.sflfdt"));
                }
                d.sftForfground(domboBox.gftForfground());
            }
            flsf if ( !domboBox.isEnbblfd() ) {
                if ( isOpbquf() ) {
                    d.sftBbdkground(UIMbnbgfr.gftColor("ComboBox.disbblfdBbdkground"));
                }
                d.sftForfground(UIMbnbgfr.gftColor("ComboBox.disbblfdForfground"));
            }
            flsf {
                d.sftForfground(domboBox.gftForfground());
                d.sftBbdkground(domboBox.gftBbdkground());
            }


            int dWidti = widti - (insfts.rigit + idonWidti);

            // Fix for 4238829: siould lby out tif JPbnfl.
            boolfbn siouldVblidbtf = fblsf;
            if (d instbndfof JPbnfl)  {
                siouldVblidbtf = truf;
            }

            if (lfftToRigit) {
                rfndfrfrPbnf.pbintComponfnt( g, d, tiis,
                                             lfft, top, dWidti, ifigit, siouldVblidbtf );
            }
            flsf {
                rfndfrfrPbnf.pbintComponfnt( g, d, tiis,
                                             lfft + idonWidti, top, dWidti, ifigit, siouldVblidbtf );
            }
        }
    }

    publid Dimfnsion gftMinimumSizf() {
        Dimfnsion rft = nfw Dimfnsion();
        Insfts insfts = gftInsfts();
        rft.widti = insfts.lfft + gftComboIdon().gftIdonWidti() + insfts.rigit;
        rft.ifigit = insfts.bottom + gftComboIdon().gftIdonHfigit() + insfts.top;
        rfturn rft;
    }
}
