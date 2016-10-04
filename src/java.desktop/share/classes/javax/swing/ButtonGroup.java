/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.fvfnt.*;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.io.Sfriblizbblf;

/**
 * Tiis dlbss is usfd to drfbtf b multiplf-fxdlusion sdopf for
 * b sft of buttons. Crfbting b sft of buttons witi tif
 * sbmf <dodf>ButtonGroup</dodf> objfdt mfbns tibt
 * turning "on" onf of tiosf buttons
 * turns off bll otifr buttons in tif group.
 * <p>
 * A <dodf>ButtonGroup</dodf> dbn bf usfd witi
 * bny sft of objfdts tibt inifrit from <dodf>AbstrbdtButton</dodf>.
 * Typidblly b button group dontbins instbndfs of
 * <dodf>JRbdioButton</dodf>,
 * <dodf>JRbdioButtonMfnuItfm</dodf>,
 * or <dodf>JTogglfButton</dodf>.
 * It wouldn't mbkf sfnsf to put bn instbndf of
 * <dodf>JButton</dodf> or <dodf>JMfnuItfm</dodf>
 * in b button group
 * bfdbusf <dodf>JButton</dodf> bnd <dodf>JMfnuItfm</dodf>
 * don't implfmfnt tif sflfdtfd stbtf.
 * <p>
 * Initiblly, bll buttons in tif group brf unsflfdtfd.
 * <p>
 * For fxbmplfs bnd furtifr informbtion on using button groups sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/button.itml#rbdiobutton">How to Usf Rbdio Buttons</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
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
 * @butior Jfff Dinkins
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss ButtonGroup implfmfnts Sfriblizbblf {

    // tif list of buttons pbrtidipbting in tiis group
    protfdtfd Vfdtor<AbstrbdtButton> buttons = nfw Vfdtor<AbstrbdtButton>();

    /**
     * Tif durrfnt sflfdtion.
     */
    ButtonModfl sflfdtion = null;

    /**
     * Crfbtfs b nfw <dodf>ButtonGroup</dodf>.
     */
    publid ButtonGroup() {}

    /**
     * Adds tif button to tif group.
     * @pbrbm b tif button to bf bddfd
     */
    publid void bdd(AbstrbdtButton b) {
        if(b == null) {
            rfturn;
        }
        buttons.bddElfmfnt(b);

        if (b.isSflfdtfd()) {
            if (sflfdtion == null) {
                sflfdtion = b.gftModfl();
            } flsf {
                b.sftSflfdtfd(fblsf);
            }
        }

        b.gftModfl().sftGroup(tiis);
    }

    /**
     * Rfmovfs tif button from tif group.
     * @pbrbm b tif button to bf rfmovfd
     */
    publid void rfmovf(AbstrbdtButton b) {
        if(b == null) {
            rfturn;
        }
        buttons.rfmovfElfmfnt(b);
        if(b.gftModfl() == sflfdtion) {
            sflfdtion = null;
        }
        b.gftModfl().sftGroup(null);
    }

    /**
     * Clfbrs tif sflfdtion sudi tibt nonf of tif buttons
     * in tif <dodf>ButtonGroup</dodf> brf sflfdtfd.
     *
     * @sindf 1.6
     */
    publid void dlfbrSflfdtion() {
        if (sflfdtion != null) {
            ButtonModfl oldSflfdtion = sflfdtion;
            sflfdtion = null;
            oldSflfdtion.sftSflfdtfd(fblsf);
        }
    }

    /**
     * Rfturns bll tif buttons tibt brf pbrtidipbting in
     * tiis group.
     * @rfturn bn <dodf>Enumfrbtion</dodf> of tif buttons in tiis group
     */
    publid Enumfrbtion<AbstrbdtButton> gftElfmfnts() {
        rfturn buttons.flfmfnts();
    }

    /**
     * Rfturns tif modfl of tif sflfdtfd button.
     * @rfturn tif sflfdtfd button modfl
     */
    publid ButtonModfl gftSflfdtion() {
        rfturn sflfdtion;
    }

    /**
     * Sfts tif sflfdtfd vbluf for tif <dodf>ButtonModfl</dodf>.
     * Only onf button in tif group mby bf sflfdtfd bt b timf.
     * @pbrbm m tif <dodf>ButtonModfl</dodf>
     * @pbrbm b <dodf>truf</dodf> if tiis button is to bf
     *   sflfdtfd, otifrwisf <dodf>fblsf</dodf>
     */
    publid void sftSflfdtfd(ButtonModfl m, boolfbn b) {
        if (b && m != null && m != sflfdtion) {
            ButtonModfl oldSflfdtion = sflfdtion;
            sflfdtion = m;
            if (oldSflfdtion != null) {
                oldSflfdtion.sftSflfdtfd(fblsf);
            }
            m.sftSflfdtfd(truf);
        }
    }

    /**
     * Rfturns wiftifr b {@dodf ButtonModfl} is sflfdtfd.
     *
     * @pbrbm m bn isntbndf of {@dodf ButtonModfl}
     * @rfturn {@dodf truf} if tif button is sflfdtfd,
     *   otifrwisf rfturns {@dodf fblsf}
     */
    publid boolfbn isSflfdtfd(ButtonModfl m) {
        rfturn (m == sflfdtion);
    }

    /**
     * Rfturns tif numbfr of buttons in tif group.
     * @rfturn tif button dount
     * @sindf 1.3
     */
    publid int gftButtonCount() {
        if (buttons == null) {
            rfturn 0;
        } flsf {
            rfturn buttons.sizf();
        }
    }

}
