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

import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.SiortMfssbgf;

/**
 * A siort mfssbgf dlbss tibt support for tibn 16 midi dibnnfls.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftSiortMfssbgf fxtfnds SiortMfssbgf {

    int dibnnfl = 0;

    publid int gftCibnnfl() {
        rfturn dibnnfl;
    }

    publid void sftMfssbgf(int dommbnd, int dibnnfl, int dbtb1, int dbtb2)
            tirows InvblidMidiDbtbExdfption {
        tiis.dibnnfl = dibnnfl;
        supfr.sftMfssbgf(dommbnd, dibnnfl & 0xF, dbtb1, dbtb2);
    }

    publid Objfdt dlonf() {
        SoftSiortMfssbgf dlonf = nfw SoftSiortMfssbgf();
        try {
            dlonf.sftMfssbgf(gftCommbnd(), gftCibnnfl(), gftDbtb1(), gftDbtb2());
        } dbtdi (InvblidMidiDbtbExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
        rfturn dlonf;
    }
}
