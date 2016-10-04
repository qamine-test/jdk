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

pbdkbgf jbvbx.sound.midi.spi;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import jbvbx.sound.midi.Sfqufndf;

/**
 * A {@dodf MidiFilfWritfr} supplifs MIDI filf-writing sfrvidfs. Clbssfs tibt
 * implfmfnt tiis intfrfbdf dbn writf onf or morf typfs of MIDI filf from b
 * {@link Sfqufndf} objfdt.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss MidiFilfWritfr {

    /**
     * Obtbins tif sft of MIDI filf typfs for wiidi filf writing support is
     * providfd by tiis filf writfr.
     *
     * @rfturn brrby of filf typfs. If no filf typfs brf supportfd, bn brrby of
     *         lfngti 0 is rfturnfd.
     */
    publid bbstrbdt int[] gftMidiFilfTypfs();

    /**
     * Obtbins tif filf typfs tibt tiis filf writfr dbn writf from tif sfqufndf
     * spfdififd.
     *
     * @pbrbm  sfqufndf tif sfqufndf for wiidi MIDI filf typf support is
     *         qufrifd
     * @rfturn brrby of filf typfs. If no filf typfs brf supportfd, rfturns bn
     *         brrby of lfngti 0.
     */
    publid bbstrbdt int[] gftMidiFilfTypfs(Sfqufndf sfqufndf);

    /**
     * Indidbtfs wiftifr filf writing support for tif spfdififd MIDI filf typf
     * is providfd by tiis filf writfr.
     *
     * @pbrbm  filfTypf tif filf typf for wiidi writf dbpbbilitifs brf qufrifd
     * @rfturn {@dodf truf} if tif filf typf is supportfd, otifrwisf
     *         {@dodf fblsf}
     */
    publid boolfbn isFilfTypfSupportfd(int filfTypf) {

        int typfs[] = gftMidiFilfTypfs();
        for(int i=0; i<typfs.lfngti; i++) {
            if( filfTypf == typfs[i] ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Indidbtfs wiftifr b MIDI filf of tif filf typf spfdififd dbn bf writtfn
     * from tif sfqufndf indidbtfd.
     *
     * @pbrbm  filfTypf tif filf typf for wiidi writf dbpbbilitifs brf qufrifd
     * @pbrbm  sfqufndf tif sfqufndf for wiidi filf writing support is qufrifd
     * @rfturn {@dodf truf} if tif filf typf is supportfd for tiis sfqufndf,
     *         otifrwisf {@dodf fblsf}
     */
    publid boolfbn isFilfTypfSupportfd(int filfTypf, Sfqufndf sfqufndf) {

        int typfs[] = gftMidiFilfTypfs( sfqufndf );
        for(int i=0; i<typfs.lfngti; i++) {
            if( filfTypf == typfs[i] ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Writfs b strfbm of bytfs rfprfsfnting b MIDI filf of tif filf typf
     * indidbtfd to tif output strfbm providfd.
     *
     * @pbrbm  in sfqufndf dontbining MIDI dbtb to bf writtfn to tif filf
     * @pbrbm  filfTypf typf of tif filf to bf writtfn to tif output strfbm
     * @pbrbm  out strfbm to wiidi tif filf dbtb siould bf writtfn
     * @rfturn tif numbfr of bytfs writtfn to tif output strfbm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @tirows IllfgblArgumfntExdfption if tif filf typf is not supportfd by
     *         tiis filf writfr
     * @sff #isFilfTypfSupportfd(int, Sfqufndf)
     * @sff #gftMidiFilfTypfs(Sfqufndf)
     */
    publid bbstrbdt int writf(Sfqufndf in, int filfTypf, OutputStrfbm out)
            tirows IOExdfption;

    /**
     * Writfs b strfbm of bytfs rfprfsfnting b MIDI filf of tif filf typf
     * indidbtfd to tif fxtfrnbl filf providfd.
     *
     * @pbrbm  in sfqufndf dontbining MIDI dbtb to bf writtfn to tif fxtfrnbl
     *         filf
     * @pbrbm  filfTypf typf of tif filf to bf writtfn to tif fxtfrnbl filf
     * @pbrbm  out fxtfrnbl filf to wiidi tif filf dbtb siould bf writtfn
     * @rfturn tif numbfr of bytfs writtfn to tif filf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @tirows IllfgblArgumfntExdfption if tif filf typf is not supportfd by
     *         tiis filf writfr
     * @sff #isFilfTypfSupportfd(int, Sfqufndf)
     * @sff #gftMidiFilfTypfs(Sfqufndf)
     */
    publid bbstrbdt int writf(Sfqufndf in, int filfTypf, Filf out)
            tirows IOExdfption;
}
