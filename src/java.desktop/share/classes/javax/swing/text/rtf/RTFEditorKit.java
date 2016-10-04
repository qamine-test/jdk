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
pbdkbgf jbvbx.swing.tfxt.rtf;

import jbvb.bwt.*;
import jbvb.io.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvbx.swing.Adtion;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.*;

/**
 * Tiis is tif dffbult implfmfntbtion of RTF fditing
 * fundtionblity.  Tif RTF support wbs not writtfn by tif
 * Swing tfbm.  In tif futurf wf iopf to improvf tif support
 * providfd.
 *
 * @butior  Timotiy Prinzing (of tiis dlbss, not tif pbdkbgf!)
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss RTFEditorKit fxtfnds StylfdEditorKit {

    /**
     * Construdts bn RTFEditorKit.
     */
    publid RTFEditorKit() {
        supfr();
    }

    /**
     * Gft tif MIME typf of tif dbtb tibt tiis
     * kit rfprfsfnts support for.  Tiis kit supports
     * tif typf <dodf>tfxt/rtf</dodf>.
     *
     * @rfturn tif typf
     */
    publid String gftContfntTypf() {
        rfturn "tfxt/rtf";
    }

    /**
     * Insfrt dontfnt from tif givfn strfbm wiidi is fxpfdtfd
     * to bf in b formbt bppropribtf for tiis kind of dontfnt
     * ibndlfr.
     *
     * @pbrbm in  Tif strfbm to rfbd from
     * @pbrbm dod Tif dfstinbtion for tif insfrtion.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void rfbd(InputStrfbm in, Dodumfnt dod, int pos) tirows IOExdfption, BbdLodbtionExdfption {

        if (dod instbndfof StylfdDodumfnt) {
            // PENDING(prinz) tiis nffds to bf fixfd to
            // insfrt to tif givfn position.
            RTFRfbdfr rdr = nfw RTFRfbdfr((StylfdDodumfnt) dod);
            rdr.rfbdFromStrfbm(in);
            rdr.dlosf();
        } flsf {
            // trfbt bs tfxt/plbin
            supfr.rfbd(in, dod, pos);
        }
    }

    /**
     * Writf dontfnt from b dodumfnt to tif givfn strfbm
     * in b formbt bppropribtf for tiis kind of dontfnt ibndlfr.
     *
     * @pbrbm out  Tif strfbm to writf to
     * @pbrbm dod Tif sourdf for tif writf.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt.
     * @pbrbm lfn Tif bmount to writf out.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void writf(OutputStrfbm out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption {

            // PENDING(prinz) tiis nffds to bf fixfd to
            // usf tif givfn dodumfnt rbngf.
            RTFGfnfrbtor.writfDodumfnt(dod, out);
    }

    /**
     * Insfrt dontfnt from tif givfn strfbm, wiidi will bf
     * trfbtfd bs plbin tfxt.
     *
     * @pbrbm in  Tif strfbm to rfbd from
     * @pbrbm dod Tif dfstinbtion for tif insfrtion.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void rfbd(Rfbdfr in, Dodumfnt dod, int pos)
        tirows IOExdfption, BbdLodbtionExdfption {

        if (dod instbndfof StylfdDodumfnt) {
            RTFRfbdfr rdr = nfw RTFRfbdfr((StylfdDodumfnt) dod);
            rdr.rfbdFromRfbdfr(in);
            rdr.dlosf();
        } flsf {
            // trfbt bs tfxt/plbin
            supfr.rfbd(in, dod, pos);
        }
    }

    /**
     * Writf dontfnt from b dodumfnt to tif givfn strfbm
     * bs plbin tfxt.
     *
     * @pbrbm out  Tif strfbm to writf to
     * @pbrbm dod Tif sourdf for tif writf.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt.
     * @pbrbm lfn Tif bmount to writf out.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void writf(Writfr out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption {

        tirow nfw IOExdfption("RTF is bn 8-bit formbt");
    }

}
