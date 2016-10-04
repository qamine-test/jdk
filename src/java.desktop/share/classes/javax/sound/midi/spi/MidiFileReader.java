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
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nft.URL;

import jbvbx.sound.midi.MidiFilfFormbt;
import jbvbx.sound.midi.Sfqufndf;
import jbvbx.sound.midi.InvblidMidiDbtbExdfption;

/**
 * A {@dodf MidiFilfRfbdfr} supplifs MIDI filf-rfbding sfrvidfs. Clbssfs
 * implfmfnting tiis intfrfbdf dbn pbrsf tif formbt informbtion from onf or morf
 * typfs of MIDI filf, bnd dbn produdf b {@link Sfqufndf} objfdt from filfs of
 * tifsf typfs.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss MidiFilfRfbdfr {

    /**
     * Obtbins tif MIDI filf formbt of tif input strfbm providfd. Tif strfbm
     * must point to vblid MIDI filf dbtb. In gfnfrbl, MIDI filf rfbdfrs mby
     * nffd to rfbd somf dbtb from tif strfbm bfforf dftfrmining wiftifr tify
     * support it. Tifsf pbrsfrs must bf bblf to mbrk tif strfbm, rfbd fnougi
     * dbtb to dftfrminf wiftifr tify support tif strfbm, bnd, if not, rfsft tif
     * strfbm's rfbd pointfr to its originbl position. If tif input strfbm dofs
     * not support tiis, tiis mftiod mby fbil witi bn {@dodf IOExdfption}.
     *
     * @pbrbm  strfbm tif input strfbm from wiidi filf formbt informbtion
     *         siould bf fxtrbdtfd
     * @rfturn b {@dodf MidiFilfFormbt} objfdt dfsdribing tif MIDI filf formbt
     * @tirows InvblidMidiDbtbExdfption if tif strfbm dofs not point to vblid
     *         MIDI filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sff InputStrfbm#mbrkSupportfd
     * @sff InputStrfbm#mbrk
     */
    publid bbstrbdt MidiFilfFormbt gftMidiFilfFormbt(InputStrfbm strfbm)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins tif MIDI filf formbt of tif URL providfd. Tif URL must point to
     * vblid MIDI filf dbtb.
     *
     * @pbrbm  url tif URL from wiidi filf formbt informbtion siould bf
     *         fxtrbdtfd
     * @rfturn b {@dodf MidiFilfFormbt} objfdt dfsdribing tif MIDI filf formbt
     * @tirows InvblidMidiDbtbExdfption if tif URL dofs not point to vblid MIDI
     *         filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid bbstrbdt MidiFilfFormbt gftMidiFilfFormbt(URL url)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins tif MIDI filf formbt of tif {@dodf Filf} providfd. Tif
     * {@dodf Filf} must point to vblid MIDI filf dbtb.
     *
     * @pbrbm  filf tif {@dodf Filf} from wiidi filf formbt informbtion siould
     *         bf fxtrbdtfd
     * @rfturn b {@dodf MidiFilfFormbt} objfdt dfsdribing tif MIDI filf formbt
     * @tirows InvblidMidiDbtbExdfption if tif {@dodf Filf} dofs not point to
     *         vblid MIDI filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid bbstrbdt MidiFilfFormbt gftMidiFilfFormbt(Filf filf)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins b MIDI sfqufndf from tif input strfbm providfd. Tif strfbm must
     * point to vblid MIDI filf dbtb. In gfnfrbl, MIDI filf rfbdfrs mby nffd to
     * rfbd somf dbtb from tif strfbm bfforf dftfrmining wiftifr tify support
     * it. Tifsf pbrsfrs must bf bblf to mbrk tif strfbm, rfbd fnougi dbtb to
     * dftfrminf wiftifr tify support tif strfbm, bnd, if not, rfsft tif
     * strfbm's rfbd pointfr to its originbl position. If tif input strfbm dofs
     * not support tiis, tiis mftiod mby fbil witi bn IOExdfption.
     *
     * @pbrbm  strfbm tif input strfbm from wiidi tif {@dodf Sfqufndf} siould
     *         bf donstrudtfd
     * @rfturn b {@dodf Sfqufndf} objfdt bbsfd on tif MIDI filf dbtb dontbinfd
     *         in tif input strfbm.
     * @tirows InvblidMidiDbtbExdfption if tif strfbm dofs not point to vblid
     *         MIDI filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sff InputStrfbm#mbrkSupportfd
     * @sff InputStrfbm#mbrk
     */
    publid bbstrbdt Sfqufndf gftSfqufndf(InputStrfbm strfbm)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins b MIDI sfqufndf from tif URL providfd. Tif URL must point to
     * vblid MIDI filf dbtb.
     *
     * @pbrbm  url tif URL for wiidi tif {@dodf Sfqufndf} siould bf donstrudtfd
     * @rfturn b {@dodf Sfqufndf} objfdt bbsfd on tif MIDI filf dbtb pointfd to
     *         by tif URL
     * @tirows InvblidMidiDbtbExdfption if tif URL dofs not point to vblid MIDI
     *         filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid bbstrbdt Sfqufndf gftSfqufndf(URL url)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins b MIDI sfqufndf from tif {@dodf Filf} providfd. Tif {@dodf Filf}
     * must point to vblid MIDI filf dbtb.
     *
     * @pbrbm  filf tif {@dodf Filf} from wiidi tif {@dodf Sfqufndf} siould bf
     *         donstrudtfd
     * @rfturn b {@dodf Sfqufndf} objfdt bbsfd on tif MIDI filf dbtb pointfd to
     *         by tif {@dodf Filf}
     * @tirows InvblidMidiDbtbExdfption if tif {@dodf Filf} dofs not point to
     *         vblid MIDI filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid bbstrbdt Sfqufndf gftSfqufndf(Filf filf)
            tirows InvblidMidiDbtbExdfption, IOExdfption;
}
