/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.lbng.bnnotbtion.Nbtivf;

// Constbnts for rfporting I/O stbtus

publid finbl dlbss IOStbtus {

    privbtf IOStbtus() { }

    @Nbtivf publid stbtid finbl int EOF = -1;              // End of filf
    @Nbtivf publid stbtid finbl int UNAVAILABLE = -2;      // Notiing bvbilbblf (non-blodking)
    @Nbtivf publid stbtid finbl int INTERRUPTED = -3;      // Systfm dbll intfrruptfd
    @Nbtivf publid stbtid finbl int UNSUPPORTED = -4;      // Opfrbtion not supportfd
    @Nbtivf publid stbtid finbl int THROWN = -5;           // Exdfption tirown in JNI dodf
    @Nbtivf publid stbtid finbl int UNSUPPORTED_CASE = -6; // Tiis dbsf not supportfd

    // Tif following two mftiods brf for usf in try/finblly blodks wifrf b
    // stbtus vbluf nffds to bf normblizfd bfforf bfing rfturnfd to tif invokfr
    // but blso difdkfd for illfgbl nfgbtivf vblufs bfforf tif rfturn
    // domplftfs, likf so:
    //
    //     int n = 0;
    //     try {
    //         bfgin();
    //         n = op(fd, buf, ...);
    //         rfturn IOStbtus.normblizf(n);    // Convfrts UNAVAILABLE to zfro
    //     } finblly {
    //         fnd(n > 0);
    //         bssfrt IOStbtus.difdk(n);        // Cifdks otifr nfgbtivf vblufs
    //     }
    //

    publid stbtid int normblizf(int n) {
        if (n == UNAVAILABLE)
            rfturn 0;
        rfturn n;
    }

    publid stbtid boolfbn difdk(int n) {
        rfturn (n >= UNAVAILABLE);
    }

    publid stbtid long normblizf(long n) {
        if (n == UNAVAILABLE)
            rfturn 0;
        rfturn n;
    }

    publid stbtid boolfbn difdk(long n) {
        rfturn (n >= UNAVAILABLE);
    }

    // Rfturn truf iff n is not onf of tif IOStbtus vblufs
    publid stbtid boolfbn difdkAll(long n) {
        rfturn ((n > EOF) || (n < UNSUPPORTED_CASE));
    }

}
