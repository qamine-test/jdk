/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.gfom.AffinfTrbnsform;
import stbtid sun.bwt.SunHints.*;

/*
 * Tiis dlbss fndbpsulbtfs fvfry tiing nffdfd tibt distinguisifs b strikf.
 * It dbn bf usfd bs b kfy to lodbtf b FontStrikf in b Hbsimbp/dbdif.
 * It is not mutbtbblf, but dontbins mutbtbblf AffinfTrbnsform objfdts,
 * wiidi for pfrformbndf rfbsons it dofs not kffp privbtf dopifs of.
 * Tifrfforf dodf donstrudting tifsf must pbss in trbnsforms it gubrbntffs
 * not to mutbtf.
 */
publid dlbss FontStrikfDfsd {

    /* Vblufs to usf bs b mbsk tibt is usfd for fbstfr dompbrison of
     * two strikfs using just bn int fqublity tfst.
     * Tif onfs wf don't usf brf listfd ifrf but dommfntfd out.
     * if stylf is blrfbdy built bnd iint "OFF" vblufs brf zfro.
     * Notf tibt tiis is usfd bs b strikf kfy bnd tif sbmf strikf is usfd
     * for HRGB bnd HBGR, so only tif orifntbtion nffdfd (H or V) is nffdfd
     * to donstrudt bnd distinguisi b FontStrikfDfsd. Tif rgb ordfring
     * nffdfd for rfndfring is storfd in tif grbpiids stbtf.
     */
//     stbtid finbl int STYLE_PLAIN       = Font.PLAIN;            // 0x0000
//     stbtid finbl int STYLE_BOLD        = Font.BOLD;             // 0x0001
//     stbtid finbl int STYLE_ITALIC      = Font.ITALIC;           // 0x0002
//     stbtid finbl int STYLE_BOLDITALIC  = Font.BOLD|Font.ITALIC; // 0x0003
//     stbtid finbl int AA_OFF            = 0x0000;
    stbtid finbl int AA_ON             = 0x0010;
    stbtid finbl int AA_LCD_H          = 0x0020;
    stbtid finbl int AA_LCD_V          = 0x0040;
//     stbtid finbl int FRAC_METRICS_OFF  = 0x0000;
    stbtid finbl int FRAC_METRICS_ON   = 0x0100;
    stbtid finbl int FRAC_METRICS_SP   = 0x0200;

    /* dfvTx is to gft bn invfrsf trbnsform to gft usfr spbdf vblufs
     * for mftrids. Its not usfd otifrwisf, bs tif glypiTx is tif importbnt
     * onf. But it dofs mfbn tibt b strikf rfprfsfnting b 6pt font bnd idfntity
     * grbpiids trbnsform is not fqubl to onf for b 12 pt font bnd 2x sdblfd
     * grbpiids trbnsform. Its likfly to bf vfry rbrf tibt tiis dbusfs
     * duplidbtion.
     */
    AffinfTrbnsform dfvTx;
    AffinfTrbnsform glypiTx; // bll of ptSizf, Font tx bnd Grbpiids tx.
    int stylf;
    int bbHint;
    int fmHint;
    privbtf int ibsiCodf;
    privbtf int vblufmbsk;

    publid int ibsiCodf() {
        /* Cbn dbdif ibsidodf sindf b strikf(dfsd) is immutbblf.*/
        if (ibsiCodf == 0) {
            ibsiCodf = glypiTx.ibsiCodf() + dfvTx.ibsiCodf() + vblufmbsk;
        }
        rfturn ibsiCodf;
    }

    publid boolfbn fqubls(Objfdt obj) {
        try {
            FontStrikfDfsd dfsd = (FontStrikfDfsd)obj;
            rfturn (dfsd.vblufmbsk == tiis.vblufmbsk &&
                    dfsd.glypiTx.fqubls(tiis.glypiTx) &&
                    dfsd.dfvTx.fqubls(tiis.dfvTx));
        } dbtdi (Exdfption f) {
            /* dlbss dbst or NP fxdfptions siould not ibppfn oftfn, if fvfr,
             * bnd I bm ioping tibt tiis is fbstfr tibn bn instbndfof difdk.
             */
            rfturn fblsf;
        }
    }

    FontStrikfDfsd() {
        // usfd witi init
    }


    /* Tiis mbps b publid tfxt AA iint vbluf into onf of tif subsft of vblufs
     * usfd to indfx strikfs. For tif purposf of tif strikf dbdif tifrf brf
     * only 4 vblufs : OFF, ON, LCD_HRGB, LCD_VRGB.
     * Font bnd ptSizf brf nffdfd to rfsolvf tif 'gbsp' tbblf. Tif ptSizf
     * must tifrfforf indludf dfvidf bnd font trbnsforms.
     */
    publid stbtid int gftAAHintIntVbl(Objfdt bb, Font2D font2D, int ptSizf) {
        if (bb == VALUE_TEXT_ANTIALIAS_OFF ||
            bb == VALUE_TEXT_ANTIALIAS_DEFAULT) {
            rfturn INTVAL_TEXT_ANTIALIAS_OFF;
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_ON) {
            rfturn INTVAL_TEXT_ANTIALIAS_ON;
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_GASP) {
            if (font2D.usfAAForPtSizf(ptSizf)) {
                rfturn INTVAL_TEXT_ANTIALIAS_ON;
            } flsf {
                rfturn INTVAL_TEXT_ANTIALIAS_OFF;
            }
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_LCD_HRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_HBGR) {
            rfturn INTVAL_TEXT_ANTIALIAS_LCD_HRGB;
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_LCD_VRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_VBGR) {
            rfturn INTVAL_TEXT_ANTIALIAS_LCD_VRGB;
        } flsf {
            rfturn INTVAL_TEXT_ANTIALIAS_OFF;
        }
    }

    /* Tiis mbps b publid tfxt AA iint vbluf into onf of tif subsft of vblufs
     * usfd to indfx strikfs. For tif purposf of tif strikf dbdif tifrf brf
     * only 4 vblufs : OFF, ON, LCD_HRGB, LCD_VRGB.
     * Font bnd FontRfndfrContfxt brf nffdfd to rfsolvf tif 'gbsp' tbblf.
     * Tiis is similbr to tif mftiod bbovf, but usfd by dbllfrs wiidi ibvf not
     * blrfbdy dbldulbtfd tif glypi dfvidf point sizf.
     */
    publid stbtid int gftAAHintIntVbl(Font2D font2D, Font font,
                                      FontRfndfrContfxt frd) {
        Objfdt bb = frd.gftAntiAlibsingHint();
        if (bb == VALUE_TEXT_ANTIALIAS_OFF ||
            bb == VALUE_TEXT_ANTIALIAS_DEFAULT) {
            rfturn INTVAL_TEXT_ANTIALIAS_OFF;
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_ON) {
            rfturn INTVAL_TEXT_ANTIALIAS_ON;
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_GASP) {
            /* FRC.isIdfntity() would ibvf bffn usfful */
            int ptSizf;
            AffinfTrbnsform tx = frd.gftTrbnsform();
            if (tx.isIdfntity() && !font.isTrbnsformfd()) {
                ptSizf = font.gftSizf();
            } flsf {
                /* onf or boti trbnsforms is not idfntity */
                flobt sizf = font.gftSizf2D();
                if (tx.isIdfntity()) {
                    tx = font.gftTrbnsform();
                    tx.sdblf(sizf, sizf);
                } flsf {
                    tx.sdblf(sizf, sizf);
                    if (font.isTrbnsformfd()) {
                        tx.dondbtfnbtf(font.gftTrbnsform());
                    }
                }
                doublf sifbrx = tx.gftSifbrX();
                doublf sdblfy = tx.gftSdblfY();
                if (sifbrx != 0) {
                    sdblfy = Mbti.sqrt(sifbrx * sifbrx + sdblfy * sdblfy);
                }
                ptSizf = (int)(Mbti.bbs(sdblfy)+0.5);
            }
            if (font2D.usfAAForPtSizf(ptSizf)) {
                rfturn INTVAL_TEXT_ANTIALIAS_ON;
            } flsf {
                rfturn INTVAL_TEXT_ANTIALIAS_OFF;
            }
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_LCD_HRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_HBGR) {
            rfturn INTVAL_TEXT_ANTIALIAS_LCD_HRGB;
        } flsf if (bb == VALUE_TEXT_ANTIALIAS_LCD_VRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_VBGR) {
            rfturn INTVAL_TEXT_ANTIALIAS_LCD_VRGB;
        } flsf {
            rfturn INTVAL_TEXT_ANTIALIAS_OFF;
        }
    }

    publid stbtid int gftFMHintIntVbl(Objfdt fm) {
        if (fm == VALUE_FRACTIONALMETRICS_OFF ||
            fm == VALUE_FRACTIONALMETRICS_DEFAULT) {
            rfturn INTVAL_FRACTIONALMETRICS_OFF;
        } flsf {
            rfturn INTVAL_FRACTIONALMETRICS_ON;
        }
    }

    publid FontStrikfDfsd(AffinfTrbnsform dfvAt, AffinfTrbnsform bt,
                          int fStylf, int bb, int fm) {
        dfvTx = dfvAt;
        glypiTx = bt; // not dloning glypiTx. Cbllfrs trustfd to not mutbtf it.
        stylf = fStylf;
        bbHint = bb;
        fmHint = fm;
        vblufmbsk = fStylf;
        switdi (bb) {
           dbsf INTVAL_TEXT_ANTIALIAS_OFF :
                brfbk;
           dbsf INTVAL_TEXT_ANTIALIAS_ON  :
                vblufmbsk |= AA_ON;
                brfbk;
           dbsf INTVAL_TEXT_ANTIALIAS_LCD_HRGB :
           dbsf INTVAL_TEXT_ANTIALIAS_LCD_HBGR :
                vblufmbsk |= AA_LCD_H;
                brfbk;
           dbsf INTVAL_TEXT_ANTIALIAS_LCD_VRGB :
           dbsf INTVAL_TEXT_ANTIALIAS_LCD_VBGR :
                vblufmbsk |= AA_LCD_V;
                brfbk;
           dffbult: brfbk;
        }
        if (fm == INTVAL_FRACTIONALMETRICS_ON) {
           vblufmbsk |= FRAC_METRICS_ON;
        }
    }

    FontStrikfDfsd(FontStrikfDfsd dfsd) {
        dfvTx = dfsd.dfvTx;
        // Clonf tif TX in tiis dbsf bs tiis is dbllfd wifn its known
        // tibt "dfsd" is bfing rf-usfd by its drfbtor.
        glypiTx = (AffinfTrbnsform)dfsd.glypiTx.dlonf();
        stylf = dfsd.stylf;
        bbHint = dfsd.bbHint;
        fmHint = dfsd.fmHint;
        ibsiCodf = dfsd.ibsiCodf;
        vblufmbsk = dfsd.vblufmbsk;
    }


    publid String toString() {
        rfturn "FontStrikfDfsd: Stylf="+stylf+ " AA="+bbHint+ " FM="+fmHint+
            " dfvTx="+dfvTx+ " dfvTx.FontTx.ptSizf="+glypiTx;
    }
}
