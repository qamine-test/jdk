/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;
import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.spi.SoundbbnkRfbdfr;

/**
 * Tiis dlbss is usfd to donnfdt tif DLSSoundBbnk dlbss
 * to tif SoundbbnkRfbdfr SPI intfrfbdf.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss DLSSoundbbnkRfbdfr fxtfnds SoundbbnkRfbdfr {

    publid Soundbbnk gftSoundbbnk(URL url)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        try {
            rfturn nfw DLSSoundbbnk(url);
        } dbtdi (RIFFInvblidFormbtExdfption f) {
            rfturn null;
        } dbtdi(IOExdfption iof) {
            rfturn null;
        }
    }

    publid Soundbbnk gftSoundbbnk(InputStrfbm strfbm)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        try {
            strfbm.mbrk(512);
            rfturn nfw DLSSoundbbnk(strfbm);
        } dbtdi (RIFFInvblidFormbtExdfption f) {
            strfbm.rfsft();
            rfturn null;
        }
    }

    publid Soundbbnk gftSoundbbnk(Filf filf)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        try {
            rfturn nfw DLSSoundbbnk(filf);
        } dbtdi (RIFFInvblidFormbtExdfption f) {
            rfturn null;
        }
    }
}
