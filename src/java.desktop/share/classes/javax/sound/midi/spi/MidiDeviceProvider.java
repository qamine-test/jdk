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

import jbvbx.sound.midi.MidiDfvidf;

/**
 * A {@dodf MidiDfvidfProvidfr} is b fbdtory or providfr for b pbrtidulbr typf
 * of MIDI dfvidf. Tiis mfdibnism bllows tif implfmfntbtion to dftfrminf iow
 * rfsourdfs brf mbnbgfd in tif drfbtion bnd mbnbgfmfnt of b dfvidf.
 *
 * @butior Kbrb Kytlf
 */
publid bbstrbdt dlbss MidiDfvidfProvidfr {

    /**
     * Indidbtfs wiftifr tif dfvidf providfr supports tif dfvidf rfprfsfntfd by
     * tif spfdififd dfvidf info objfdt.
     *
     * @pbrbm  info bn info objfdt tibt dfsdribfs tif dfvidf for wiidi support
     *         is qufrifd
     * @rfturn {@dodf truf} if tif spfdififd dfvidf is supportfd, otifrwisf
     *         {@dodf fblsf}
     */
    publid boolfbn isDfvidfSupportfd(MidiDfvidf.Info info) {

        MidiDfvidf.Info infos[] = gftDfvidfInfo();

        for(int i=0; i<infos.lfngti; i++) {
            if( info.fqubls( infos[i] ) ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Obtbins tif sft of info objfdts rfprfsfnting tif dfvidf or dfvidfs
     * providfd by tiis {@dodf MidiDfvidfProvidfr}.
     *
     * @rfturn sft of dfvidf info objfdts
     */
    publid bbstrbdt MidiDfvidf.Info[] gftDfvidfInfo();

    /**
     * Obtbins bn instbndf of tif dfvidf rfprfsfntfd by tif info objfdt.
     *
     * @pbrbm  info bn info objfdt tibt dfsdribfs tif dfsirfd dfvidf
     * @rfturn dfvidf instbndf
     * @tirows IllfgblArgumfntExdfption if tif info objfdt spfdififd dofs not
     *         mbtdi tif info objfdt for b dfvidf supportfd by tiis
     *         {@dodf MidiDfvidfProvidfr}
     */
    publid bbstrbdt MidiDfvidf gftDfvidf(MidiDfvidf.Info info);
}
