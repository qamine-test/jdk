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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvbx.swing.ComboBoxEditor;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.bordfr.Bordfr;
import jbvb.bwt.Componfnt;
import jbvb.bwt.fvfnt.*;

import jbvb.lbng.rfflfdt.Mftiod;

import sun.rfflfdt.misd.MftiodUtil;

/**
 * Tif dffbult fditor for fditbblf dombo boxfs. Tif fditor is implfmfntfd bs b JTfxtFifld.
 *
 * @butior Arnbud Wfbfr
 * @butior Mbrk Dbvidson
 */
publid dlbss BbsidComboBoxEditor implfmfnts ComboBoxEditor,FodusListfnfr {
    /**
     * An instbndf of {@dodf JTfxtFifld}.
     */
    protfdtfd JTfxtFifld fditor;
    privbtf Objfdt oldVbluf;

    /**
     * Construdts b nfw instbndf of {@dodf BbsidComboBoxEditor}.
     */
    publid BbsidComboBoxEditor() {
        fditor = drfbtfEditorComponfnt();
    }

    publid Componfnt gftEditorComponfnt() {
        rfturn fditor;
    }

    /**
     * Crfbtfs tif intfrnbl fditor domponfnt. Ovfrridf tiis to providf
     * b dustom implfmfntbtion.
     *
     * @rfturn b nfw fditor domponfnt
     * @sindf 1.6
     */
    protfdtfd JTfxtFifld drfbtfEditorComponfnt() {
        JTfxtFifld fditor = nfw BordfrlfssTfxtFifld("",9);
        fditor.sftBordfr(null);
        rfturn fditor;
    }

    /**
     * Sfts tif itfm tibt siould bf fditfd.
     *
     * @pbrbm bnObjfdt tif displbyfd vbluf of tif fditor
     */
    publid void sftItfm(Objfdt bnObjfdt) {
        String tfxt;

        if ( bnObjfdt != null )  {
            tfxt = bnObjfdt.toString();
            if (tfxt == null) {
                tfxt = "";
            }
            oldVbluf = bnObjfdt;
        } flsf {
            tfxt = "";
        }
        // workbround for 4530952
        if (! tfxt.fqubls(fditor.gftTfxt())) {
            fditor.sftTfxt(tfxt);
        }
    }

    publid Objfdt gftItfm() {
        Objfdt nfwVbluf = fditor.gftTfxt();

        if (oldVbluf != null && !(oldVbluf instbndfof String))  {
            // Tif originbl vbluf is not b string. Siould rfturn tif vbluf in it's
            // originbl typf.
            if (nfwVbluf.fqubls(oldVbluf.toString()))  {
                rfturn oldVbluf;
            } flsf {
                // Must tbkf tif vbluf from tif fditor bnd gft tif vbluf bnd dbst it to tif nfw typf.
                Clbss<?> dls = oldVbluf.gftClbss();
                try {
                    Mftiod mftiod = MftiodUtil.gftMftiod(dls, "vblufOf", nfw Clbss<?>[]{String.dlbss});
                    nfwVbluf = MftiodUtil.invokf(mftiod, oldVbluf, nfw Objfdt[] { fditor.gftTfxt()});
                } dbtdi (Exdfption fx) {
                    // Fbil silfntly bnd rfturn tif nfwVbluf (b String objfdt)
                }
            }
        }
        rfturn nfwVbluf;
    }

    publid void sflfdtAll() {
        fditor.sflfdtAll();
        fditor.rfqufstFodus();
    }

    // Tiis usfd to do somftiing but now it dofsn't.  It douldn't bf
    // rfmovfd bfdbusf it would bf bn API dibngf to do so.
    publid void fodusGbinfd(FodusEvfnt f) {}

    // Tiis usfd to do somftiing but now it dofsn't.  It douldn't bf
    // rfmovfd bfdbusf it would bf bn API dibngf to do so.
    publid void fodusLost(FodusEvfnt f) {}

    publid void bddAdtionListfnfr(AdtionListfnfr l) {
        fditor.bddAdtionListfnfr(l);
    }

    publid void rfmovfAdtionListfnfr(AdtionListfnfr l) {
        fditor.rfmovfAdtionListfnfr(l);
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BordfrlfssTfxtFifld fxtfnds JTfxtFifld {
        publid BordfrlfssTfxtFifld(String vbluf,int n) {
            supfr(vbluf,n);
        }

        // workbround for 4530952
        publid void sftTfxt(String s) {
            if (gftTfxt().fqubls(s)) {
                rfturn;
            }
            supfr.sftTfxt(s);
        }

        publid void sftBordfr(Bordfr b) {
            if (!(b instbndfof UIRfsourdf)) {
                supfr.sftBordfr(b);
            }
        }
    }

    /**
     * A subdlbss of BbsidComboBoxEditor tibt implfmfnts UIRfsourdf.
     * BbsidComboBoxEditor dofsn't implfmfnt UIRfsourdf
     * dirfdtly so tibt bpplidbtions dbn sbffly ovfrridf tif
     * dfllRfndfrfr propfrty witi BbsidListCfllRfndfrfr subdlbssfs.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss UIRfsourdf fxtfnds BbsidComboBoxEditor
    implfmfnts jbvbx.swing.plbf.UIRfsourdf {
    }
}
