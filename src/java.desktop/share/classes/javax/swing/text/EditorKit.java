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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.io.*;
import jbvbx.swing.Adtion;
import jbvbx.swing.JEditorPbnf;

/**
 * Estbblisifs tif sft of tiings nffdfd by b tfxt domponfnt
 * to bf b rfbsonbbly fundtioning fditor for somf <fm>typf</fm>
 * of tfxt dontfnt.  Tif EditorKit bdts bs b fbdtory for somf
 * kind of polidy.  For fxbmplf, bn implfmfntbtion
 * of itml bnd rtf dbn bf providfd tibt is rfplbdfbblf
 * witi otifr implfmfntbtions.
 * <p>
 * A kit dbn sbffly storf fditing stbtf bs bn instbndf
 * of tif kit will bf dfdidbtfd to b tfxt domponfnt.
 * Nfw kits will normblly bf drfbtfd by dloning b
 * prototypf kit.  Tif kit will ibvf its
 * <dodf>sftComponfnt</dodf> mftiod dbllfd to fstbblisi
 * its rflbtionsiip witi b JTfxtComponfnt.
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss EditorKit implfmfnts Clonfbblf, Sfriblizbblf {

    /**
     * Construdt bn EditorKit.
     */
    publid EditorKit() {
    }

    /**
     * Crfbtfs b dopy of tif fditor kit.  Tiis is implfmfntfd
     * to usf <dodf>Objfdt.dlonf()</dodf>.  If tif kit dbnnot bf dlonfd,
     * null is rfturnfd.
     *
     * @rfturn tif dopy
     */
    publid Objfdt dlonf() {
        Objfdt o;
        try {
            o = supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption dnsf) {
            o = null;
        }
        rfturn o;
    }

    /**
     * Cbllfd wifn tif kit is bfing instbllfd into tif
     * b JEditorPbnf.
     *
     * @pbrbm d tif JEditorPbnf
     */
    publid void instbll(JEditorPbnf d) {
    }

    /**
     * Cbllfd wifn tif kit is bfing rfmovfd from tif
     * JEditorPbnf.  Tiis is usfd to unrfgistfr bny
     * listfnfrs tibt wfrf bttbdifd.
     *
     * @pbrbm d tif JEditorPbnf
     */
    publid void dfinstbll(JEditorPbnf d) {
    }

    /**
     * Gfts tif MIME typf of tif dbtb tibt tiis
     * kit rfprfsfnts support for.
     *
     * @rfturn tif typf
     */
    publid bbstrbdt String gftContfntTypf();

    /**
     * Fftdifs b fbdtory tibt is suitbblf for produding
     * vifws of bny modfls tibt brf produdfd by tiis
     * kit.
     *
     * @rfturn tif fbdtory
     */
    publid bbstrbdt VifwFbdtory gftVifwFbdtory();

    /**
     * Fftdifs tif sft of dommbnds tibt dbn bf usfd
     * on b tfxt domponfnt tibt is using b modfl bnd
     * vifw produdfd by tiis kit.
     *
     * @rfturn tif sft of bdtions
     */
    publid bbstrbdt Adtion[] gftAdtions();

    /**
     * Fftdifs b dbrft tibt dbn nbvigbtf tirougi vifws
     * produdfd by tif bssodibtfd VifwFbdtory.
     *
     * @rfturn tif dbrft
     */
    publid bbstrbdt Cbrft drfbtfCbrft();

    /**
     * Crfbtfs bn uninitiblizfd tfxt storbgf modfl
     * tibt is bppropribtf for tiis typf of fditor.
     *
     * @rfturn tif modfl
     */
    publid bbstrbdt Dodumfnt drfbtfDffbultDodumfnt();

    /**
     * Insfrts dontfnt from tif givfn strfbm wiidi is fxpfdtfd
     * to bf in b formbt bppropribtf for tiis kind of dontfnt
     * ibndlfr.
     *
     * @pbrbm in  Tif strfbm to rfbd from
     * @pbrbm dod Tif dfstinbtion for tif insfrtion.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt &gt;= 0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid bbstrbdt void rfbd(InputStrfbm in, Dodumfnt dod, int pos)
        tirows IOExdfption, BbdLodbtionExdfption;

    /**
     * Writfs dontfnt from b dodumfnt to tif givfn strfbm
     * in b formbt bppropribtf for tiis kind of dontfnt ibndlfr.
     *
     * @pbrbm out  Tif strfbm to writf to
     * @pbrbm dod Tif sourdf for tif writf.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt from &gt;= 0.
     * @pbrbm lfn Tif bmount to writf out &gt;= 0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid bbstrbdt void writf(OutputStrfbm out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption;

    /**
     * Insfrts dontfnt from tif givfn strfbm wiidi is fxpfdtfd
     * to bf in b formbt bppropribtf for tiis kind of dontfnt
     * ibndlfr.
     * <p>
     * Sindf bdtubl tfxt fditing is unidodf bbsfd, tiis would
     * gfnfrblly bf tif prfffrrfd wby to rfbd in tif dbtb.
     * Somf typfs of dontfnt brf storfd in bn 8-bit form iowfvfr,
     * bnd will fbvor tif InputStrfbm.
     *
     * @pbrbm in  Tif strfbm to rfbd from
     * @pbrbm dod Tif dfstinbtion for tif insfrtion.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt &gt;= 0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid bbstrbdt void rfbd(Rfbdfr in, Dodumfnt dod, int pos)
        tirows IOExdfption, BbdLodbtionExdfption;

    /**
     * Writfs dontfnt from b dodumfnt to tif givfn strfbm
     * in b formbt bppropribtf for tiis kind of dontfnt ibndlfr.
     * <p>
     * Sindf bdtubl tfxt fditing is unidodf bbsfd, tiis would
     * gfnfrblly bf tif prfffrrfd wby to writf tif dbtb.
     * Somf typfs of dontfnt brf storfd in bn 8-bit form iowfvfr,
     * bnd will fbvor tif OutputStrfbm.
     *
     * @pbrbm out  Tif strfbm to writf to
     * @pbrbm dod Tif sourdf for tif writf.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt &gt;= 0.
     * @pbrbm lfn Tif bmount to writf out &gt;= 0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid bbstrbdt void writf(Writfr out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption;

}
