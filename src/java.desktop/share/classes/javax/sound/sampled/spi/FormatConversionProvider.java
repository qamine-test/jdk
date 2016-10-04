/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.sbmplfd.spi;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;

import stbtid jbvbx.sound.sbmplfd.AudioFormbt.Endoding;

/**
 * A formbt donvfrsion providfr providfs formbt donvfrsion sfrvidfs from onf or
 * morf input formbts to onf or morf output formbts. Convfrtfrs indludf dodfds,
 * wiidi fndodf bnd/or dfdodf budio dbtb, bs wfll bs trbnsdodfrs, ftd. Formbt
 * donvfrtfrs providf mftiods for dftfrmining wibt donvfrsions brf supportfd bnd
 * for obtbining bn budio strfbm from wiidi donvfrtfd dbtb dbn bf rfbd.
 * <p>
 * Tif sourdf formbt rfprfsfnts tif formbt of tif indoming budio dbtb, wiidi
 * will bf donvfrtfd.
 * <p>
 * Tif tbrgft formbt rfprfsfnts tif formbt of tif prodfssfd, donvfrtfd budio
 * dbtb. Tiis is tif formbt of tif dbtb tibt dbn bf rfbd from tif strfbm
 * rfturnfd by onf of tif {@dodf gftAudioInputStrfbm} mftiods.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss FormbtConvfrsionProvidfr {

    /**
     * Obtbins tif sft of sourdf formbt fndodings from wiidi formbt donvfrsion
     * sfrvidfs brf providfd by tiis providfr.
     *
     * @rfturn brrby of sourdf formbt fndodings. If for somf rfbson providfr
     *         dofs not providf bny donvfrsion sfrvidfs, bn brrby of lfngti 0 is
     *         rfturnfd.
     */
    publid bbstrbdt Endoding[] gftSourdfEndodings();

    /**
     * Obtbins tif sft of tbrgft formbt fndodings to wiidi formbt donvfrsion
     * sfrvidfs brf providfd by tiis providfr.
     *
     * @rfturn brrby of tbrgft formbt fndodings. If for somf rfbson providfr
     *         dofs not providf bny donvfrsion sfrvidfs, bn brrby of lfngti 0 is
     *         rfturnfd.
     */
    publid bbstrbdt Endoding[] gftTbrgftEndodings();

    /**
     * Indidbtfs wiftifr tif formbt donvfrtfr supports donvfrsion from tif
     * spfdififd sourdf formbt fndoding.
     *
     * @pbrbm  sourdfEndoding tif sourdf formbt fndoding for wiidi support is
     *         qufrifd
     * @rfturn {@dodf truf} if tif fndoding is supportfd, otifrwisf
     *         {@dodf fblsf}
     */
    publid boolfbn isSourdfEndodingSupportfd(Endoding sourdfEndoding) {

        Endoding sourdfEndodings[] = gftSourdfEndodings();

        for(int i=0; i<sourdfEndodings.lfngti; i++) {
            if( sourdfEndoding.fqubls( sourdfEndodings[i]) ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Indidbtfs wiftifr tif formbt donvfrtfr supports donvfrsion to tif
     * spfdififd tbrgft formbt fndoding.
     *
     * @pbrbm  tbrgftEndoding tif tbrgft formbt fndoding for wiidi support is
     *         qufrifd
     * @rfturn {@dodf truf} if tif fndoding is supportfd, otifrwisf
     *         {@dodf fblsf}
     */
    publid boolfbn isTbrgftEndodingSupportfd(Endoding tbrgftEndoding) {

        Endoding tbrgftEndodings[] = gftTbrgftEndodings();

        for(int i=0; i<tbrgftEndodings.lfngti; i++) {
            if( tbrgftEndoding.fqubls( tbrgftEndodings[i]) ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Obtbins tif sft of tbrgft formbt fndodings supportfd by tif formbt
     * donvfrtfr givfn b pbrtidulbr sourdf formbt. If no tbrgft formbt fndodings
     * brf supportfd for tiis sourdf formbt, bn brrby of lfngti 0 is rfturnfd.
     *
     * @pbrbm  sourdfFormbt formbt of tif indoming dbtb
     * @rfturn brrby of supportfd tbrgft formbt fndodings
     */
    publid bbstrbdt Endoding[] gftTbrgftEndodings(AudioFormbt sourdfFormbt);

    /**
     * Indidbtfs wiftifr tif formbt donvfrtfr supports donvfrsion to b
     * pbrtidulbr fndoding from b pbrtidulbr formbt.
     *
     * @pbrbm  tbrgftEndoding dfsirfd fndoding of tif outgoing dbtb
     * @pbrbm  sourdfFormbt formbt of tif indoming dbtb
     * @rfturn {@dodf truf} if tif donvfrsion is supportfd, otifrwisf
     *         {@dodf fblsf}
     */
    publid boolfbn isConvfrsionSupportfd(Endoding tbrgftEndoding,
                                         AudioFormbt sourdfFormbt) {

        Endoding tbrgftEndodings[] = gftTbrgftEndodings(sourdfFormbt);

        for(int i=0; i<tbrgftEndodings.lfngti; i++) {
            if( tbrgftEndoding.fqubls( tbrgftEndodings[i]) ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Obtbins tif sft of tbrgft formbts witi tif fndoding spfdififd supportfd
     * by tif formbt donvfrtfr If no tbrgft formbts witi tif spfdififd fndoding
     * brf supportfd for tiis sourdf formbt, bn brrby of lfngti 0 is rfturnfd.
     *
     * @pbrbm  tbrgftEndoding dfsirfd fndoding of tif strfbm bftfr prodfssing
     * @pbrbm  sourdfFormbt formbt of tif indoming dbtb
     * @rfturn brrby of supportfd tbrgft formbts
     */
    publid bbstrbdt AudioFormbt[] gftTbrgftFormbts(Endoding tbrgftEndoding,
                                                   AudioFormbt sourdfFormbt);

    /**
     * Indidbtfs wiftifr tif formbt donvfrtfr supports donvfrsion to onf
     * pbrtidulbr formbt from bnotifr.
     *
     * @pbrbm  tbrgftFormbt dfsirfd formbt of outgoing dbtb
     * @pbrbm  sourdfFormbt formbt of tif indoming dbtb
     * @rfturn {@dodf truf} if tif donvfrsion is supportfd, otifrwisf
     *         {@dodf fblsf}
     */
    publid boolfbn isConvfrsionSupportfd(AudioFormbt tbrgftFormbt,
                                         AudioFormbt sourdfFormbt) {

        AudioFormbt tbrgftFormbts[] = gftTbrgftFormbts( tbrgftFormbt.gftEndoding(), sourdfFormbt );

        for(int i=0; i<tbrgftFormbts.lfngti; i++) {
            if( tbrgftFormbt.mbtdifs( tbrgftFormbts[i] ) ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Obtbins bn budio input strfbm witi tif spfdififd fndoding from tif givfn
     * budio input strfbm.
     *
     * @pbrbm  tbrgftEndoding dfsirfd fndoding of tif strfbm bftfr prodfssing
     * @pbrbm  sourdfStrfbm strfbm from wiidi dbtb to bf prodfssfd siould bf
     *         rfbd
     * @rfturn strfbm from wiidi prodfssfd dbtb witi tif spfdififd tbrgft
     *         fndoding mby bf rfbd
     * @tirows IllfgblArgumfntExdfption if tif formbt dombinbtion supplifd is
     *         not supportfd
     */
    publid bbstrbdt AudioInputStrfbm gftAudioInputStrfbm(
            Endoding tbrgftEndoding, AudioInputStrfbm sourdfStrfbm);

    /**
     * Obtbins bn budio input strfbm witi tif spfdififd formbt from tif givfn
     * budio input strfbm.
     *
     * @pbrbm  tbrgftFormbt dfsirfd dbtb formbt of tif strfbm bftfr prodfssing
     * @pbrbm  sourdfStrfbm strfbm from wiidi dbtb to bf prodfssfd siould bf
     *         rfbd
     * @rfturn strfbm from wiidi prodfssfd dbtb witi tif spfdififd formbt mby bf
     *         rfbd
     * @tirows IllfgblArgumfntExdfption if tif formbt dombinbtion supplifd is
     *         not supportfd
     */
    publid bbstrbdt AudioInputStrfbm gftAudioInputStrfbm(
            AudioFormbt tbrgftFormbt, AudioInputStrfbm sourdfStrfbm);
}
