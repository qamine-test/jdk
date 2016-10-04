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
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;

import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.Soundbbnk;

/**
 * A {@dodf SoundbbnkRfbdfr} supplifs soundbbnk filf-rfbding sfrvidfs. Condrftf
 * subdlbssfs of {@dodf SoundbbnkRfbdfr} pbrsf b givfn soundbbnk filf, produding
 * b {@link jbvbx.sound.midi.Soundbbnk} objfdt tibt dbn bf lobdfd into b
 * {@link jbvbx.sound.midi.Syntifsizfr}.
 *
 * @sindf 1.3
 * @butior Kbrb Kytlf
 */
publid bbstrbdt dlbss SoundbbnkRfbdfr {

    /**
     * Obtbins b soundbbnk objfdt from tif URL providfd.
     *
     * @pbrbm  url URL rfprfsfnting tif soundbbnk.
     * @rfturn soundbbnk objfdt
     * @tirows InvblidMidiDbtbExdfption if tif URL dofs not point to vblid MIDI
     *         soundbbnk dbtb rfdognizfd by tiis soundbbnk rfbdfr
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid bbstrbdt Soundbbnk gftSoundbbnk(URL url)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins b soundbbnk objfdt from tif {@dodf InputStrfbm} providfd.
     *
     * @pbrbm  strfbm {@dodf InputStrfbm} rfprfsfnting tif soundbbnk
     * @rfturn soundbbnk objfdt
     * @tirows InvblidMidiDbtbExdfption if tif strfbm dofs not point to vblid
     *         MIDI soundbbnk dbtb rfdognizfd by tiis soundbbnk rfbdfr
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid bbstrbdt Soundbbnk gftSoundbbnk(InputStrfbm strfbm)
            tirows InvblidMidiDbtbExdfption, IOExdfption;

    /**
     * Obtbins b soundbbnk objfdt from tif {@dodf Filf} providfd.
     *
     * @pbrbm  filf tif {@dodf Filf} rfprfsfnting tif soundbbnk
     * @rfturn soundbbnk objfdt
     * @tirows InvblidMidiDbtbExdfption if tif filf dofs not point to vblid MIDI
     *         soundbbnk dbtb rfdognizfd by tiis soundbbnk rfbdfr
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid bbstrbdt Soundbbnk gftSoundbbnk(Filf filf)
            tirows InvblidMidiDbtbExdfption, IOExdfption;
}
