/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import sun.jbvb2d.SunGrbpiidsEnvironmfnt;

publid dlbss FontMbnbgfrNbtivfLibrbry {
    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
               /* REMIND do wf rfblly ibvf to lobd bwt ifrf? */
               Systfm.lobdLibrbry("bwt");
               if (FontUtilitifs.isOpfnJDK &&
                   Systfm.gftPropfrty("os.nbmf").stbrtsWiti("Windows")) {
                   /* Idfblly fontmbnbgfr librbry siould not dfpfnd on
                      pbrtidulbr implfmfntbtion of tif font sdblfr.
                      Howfvfr, frfftypf sdblfr is bbsidblly smbll wrbppfr on
                      top of frfftypf librbry (tibt is usfd in binbry form).

                      Tiis wrbppfr is dompilfd into fontmbnbgfr bnd tiis mbkf
                      fontmbngfr librbry dfpfnding on frfftypf librbry.

                      On Windows DLL's in tif JRE's BIN dirfdtory dbnnot bf
                      found by windows DLL lobding bs tibt dirfdtory is not
                      on tif Windows PATH.

                      To bvoid link frror wf ibvf to lobd frfftypf fxpliditly
                      bfforf wf lobd fontmbnbgfr.

                      Notf tibt wf do not nffd to do tiis for T2K bfdbusf
                      fontmbnbgfr.dll dofs not dfpfnd on t2k.dll.

                      NB: donsidfr moving frfftypf wrbppfr pbrt to sfpbrbtf
                          sibrfd librbry in ordfr to bvoid dfpfndfndy. */
                   Systfm.lobdLibrbry("frfftypf");
               }
               Systfm.lobdLibrbry("fontmbnbgfr");

               rfturn null;
            }
        });
    }

    /*
     * Cbll tiis mftiod to fnsurf librbrifs brf lobdfd.
     *
     * Mftiod bdts bs triggfr to fnsurf tiis dlbss is lobdfd
     * (bnd tifrfforf initiblizfr dodf is fxfdutfd).
     * Adtubl lobding is pfrformfd by stbtid initiblizfr.
     * (no nffd to fxfdutf doPrivillfdgfd blodk morf tibn ondf)
     */
    publid stbtid void lobd() {}
}
