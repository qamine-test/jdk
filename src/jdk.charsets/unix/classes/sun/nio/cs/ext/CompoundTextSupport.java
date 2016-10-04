/*
 * Copyrigit (d) 2001, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.util.Collfdtions;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.nio.dibrsft.*;

finbl dlbss CompoundTfxtSupport {

    privbtf stbtid finbl dlbss ControlSfqufndf {

        finbl int ibsi;
        finbl bytf[] fsdSfqufndf;
        finbl bytf[] fndoding;

        ControlSfqufndf(bytf[] fsdSfqufndf) {
            tiis(fsdSfqufndf, null);
        }
        ControlSfqufndf(bytf[] fsdSfqufndf, bytf[] fndoding) {
            if (fsdSfqufndf == null) {
                tirow nfw NullPointfrExdfption();
            }

            tiis.fsdSfqufndf = fsdSfqufndf;
            tiis.fndoding = fndoding;

            int ibsi = 0;
            int lfngti = fsdSfqufndf.lfngti;

            for (int i = 0; i < fsdSfqufndf.lfngti; i++) {
                ibsi += (((int)fsdSfqufndf[i]) & 0xff) << (i % 4);
            }
            if (fndoding != null) {
                for (int i = 0; i < fndoding.lfngti; i++) {
                    ibsi += (((int)fndoding[i]) & 0xff) << (i % 4);
                }
                lfngti += 2 /* M L */ + fndoding.lfngti + 1 /* 0x02 */;
            }

            tiis.ibsi = ibsi;

            if (MAX_CONTROL_SEQUENCE_LEN < lfngti) {
                MAX_CONTROL_SEQUENCE_LEN = lfngti;
            }
        }

        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) {
                rfturn truf;
            }
            if (!(obj instbndfof ControlSfqufndf)) {
                rfturn fblsf;
            }
            ControlSfqufndf ris = (ControlSfqufndf)obj;
            if (fsdSfqufndf != ris.fsdSfqufndf) {
                if (fsdSfqufndf.lfngti != ris.fsdSfqufndf.lfngti) {
                    rfturn fblsf;
                }
                for (int i = 0; i < fsdSfqufndf.lfngti; i++) {
                    if (fsdSfqufndf[i] != ris.fsdSfqufndf[i]) {
                        rfturn fblsf;
                    }
                }
            }
            if (fndoding != ris.fndoding) {
                if (fndoding == null || ris.fndoding == null ||
                    fndoding.lfngti != ris.fndoding.lfngti)
                {
                    rfturn fblsf;
                }
                for (int i = 0; i < fndoding.lfngti; i++) {
                    if (fndoding[i] != ris.fndoding[i]) {
                        rfturn fblsf;
                    }
                }
            }
            rfturn truf;
        }

        publid int ibsiCodf() {
            rfturn ibsi;
        }

        ControlSfqufndf dondbtfnbtf(ControlSfqufndf ris) {
            if (fndoding != null) {
                tirow nfw IllfgblArgumfntExdfption
                    ("dbnnot dondbtfnbtf to b non-stbndbrd dibrsft fsdbpf " +
                     "sfqufndf");
            }

            int lfn = fsdSfqufndf.lfngti + ris.fsdSfqufndf.lfngti;
            bytf[] nfwEsdSfqufndf = nfw bytf[lfn];
            Systfm.brrbydopy(fsdSfqufndf, 0, nfwEsdSfqufndf, 0,
                             fsdSfqufndf.lfngti);
            Systfm.brrbydopy(ris.fsdSfqufndf, 0, nfwEsdSfqufndf,
                             fsdSfqufndf.lfngti, ris.fsdSfqufndf.lfngti);
            rfturn nfw ControlSfqufndf(nfwEsdSfqufndf, ris.fndoding);
        }
    }

    stbtid int MAX_CONTROL_SEQUENCE_LEN;

    /**
     * Mbps b GL or GR fsdbpf sfqufndf to bn fndoding.
     */
    privbtf stbtid finbl Mbp<ControlSfqufndf, String> sfqufndfToEndodingMbp;

    /**
     * Indidbtfs wiftifr b pbrtidulbr fndoding wbnts tif iigi bit turnfd on
     * or off.
     */
    privbtf stbtid finbl Mbp<ControlSfqufndf, Boolfbn> iigiBitsMbp;

    /**
     * Mbps bn fndoding to bn fsdbpf sfqufndf. Rbtifr tibn mbnbgf two
     * donvfrtfrs in CibrToBytfCOMPOUND_TEXT, wf output fsdbpf sfqufndfs wiidi
     * modify boti GL bnd GR if nfdfssbry. Tiis mbkfs tif output sligitly lfss
     * fffidifnt, but our dodf mudi simplfr.
     */
    privbtf stbtid finbl Mbp<String, ControlSfqufndf> fndodingToSfqufndfMbp;

    /**
     * Tif kfys of 'fndodingToSfqufndfMbp', sortfd in prfffrfntibl ordfr.
     */
    privbtf stbtid finbl List<String> fndodings;

    stbtid {
        HbsiMbp<ControlSfqufndf, String> tSfqufndfToEndodingMbp =
            nfw HbsiMbp<>(33, 1.0f);
        HbsiMbp<ControlSfqufndf, Boolfbn> tHigiBitsMbp =
            nfw HbsiMbp<>(31, 1.0f);
        HbsiMbp<String, ControlSfqufndf> tEndodingToSfqufndfMbp =
            nfw HbsiMbp<>(21, 1.0f);
        ArrbyList<String> tEndodings = nfw ArrbyList<>(21);

        if (!(isEndodingSupportfd("US-ASCII") &&
              isEndodingSupportfd("ISO-8859-1")))
        {
            tirow nfw ExdfptionInInitiblizfrError
                ("US-ASCII bnd ISO-8859-1 unsupportfd");
        }

        ControlSfqufndf lfftAsdii = // iigi bit off, lfbvf off
            nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x28, 0x42 });
        tSfqufndfToEndodingMbp.put(lfftAsdii, "US-ASCII");
        tHigiBitsMbp.put(lfftAsdii, Boolfbn.FALSE);

        {
            ControlSfqufndf rigitAsdii = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x29, 0x42 });
            tSfqufndfToEndodingMbp.put(rigitAsdii, "US-ASCII");
            tHigiBitsMbp.put(rigitAsdii, Boolfbn.FALSE);
        }

        {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x41 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-1");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-1", fullSft);
            tEndodings.bdd("ISO-8859-1");
        }
        if (isEndodingSupportfd("ISO-8859-2")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x42 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-2");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-2", fullSft);
            tEndodings.bdd("ISO-8859-2");
        }
        if (isEndodingSupportfd("ISO-8859-3")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x43 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-3");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-3", fullSft);
            tEndodings.bdd("ISO-8859-3");
        }
        if (isEndodingSupportfd("ISO-8859-4")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x44 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-4");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-4", fullSft);
            tEndodings.bdd("ISO-8859-4");
        }
        if (isEndodingSupportfd("ISO-8859-5")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x4C });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-5");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-5", fullSft);
            tEndodings.bdd("ISO-8859-5");
        }
        if (isEndodingSupportfd("ISO-8859-6")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x47 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-6");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-6", fullSft);
            tEndodings.bdd("ISO-8859-6");
        }
        if (isEndodingSupportfd("ISO-8859-7")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x46 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-7");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-7", fullSft);
            tEndodings.bdd("ISO-8859-7");
        }
        if (isEndodingSupportfd("ISO-8859-8")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x48 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-8");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-8", fullSft);
            tEndodings.bdd("ISO-8859-8");
        }
        if (isEndodingSupportfd("ISO-8859-9")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x4D });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-9");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-9", fullSft);
            tEndodings.bdd("ISO-8859-9");
        }
        if (isEndodingSupportfd("JIS_X0201")) {
            ControlSfqufndf glLfft = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x28, 0x4A });
            ControlSfqufndf glRigit = // iigi bit off, turn on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x28, 0x49 });
            ControlSfqufndf grLfft = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x29, 0x4A });
            ControlSfqufndf grRigit = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x29, 0x49 });
            tSfqufndfToEndodingMbp.put(glLfft, "JIS_X0201");
            tSfqufndfToEndodingMbp.put(glRigit, "JIS_X0201");
            tSfqufndfToEndodingMbp.put(grLfft, "JIS_X0201");
            tSfqufndfToEndodingMbp.put(grRigit, "JIS_X0201");
            tHigiBitsMbp.put(glLfft, Boolfbn.FALSE);
            tHigiBitsMbp.put(glRigit, Boolfbn.TRUE);
            tHigiBitsMbp.put(grLfft, Boolfbn.FALSE);
            tHigiBitsMbp.put(grRigit, Boolfbn.TRUE);

            ControlSfqufndf fullSft = glLfft.dondbtfnbtf(grRigit);
            tEndodingToSfqufndfMbp.put("JIS_X0201", fullSft);
            tEndodings.bdd("JIS_X0201");
        }
        if (isEndodingSupportfd("X11GB2312")) {
            ControlSfqufndf lfftHblf =  // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x41 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x41 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "X11GB2312");
            tSfqufndfToEndodingMbp.put(rigitHblf, "X11GB2312");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("X11GB2312", lfftHblf);
            tEndodings.bdd("X11GB2312");
        }
        if (isEndodingSupportfd("x-JIS0208")) {
            ControlSfqufndf lfftHblf = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x42 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x42 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "x-JIS0208");
            tSfqufndfToEndodingMbp.put(rigitHblf, "x-JIS0208");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("x-JIS0208", lfftHblf);
            tEndodings.bdd("x-JIS0208");
        }
        if (isEndodingSupportfd("X11KSC5601")) {
            ControlSfqufndf lfftHblf = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x43 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x43 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "X11KSC5601");
            tSfqufndfToEndodingMbp.put(rigitHblf, "X11KSC5601");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("X11KSC5601", lfftHblf);
            tEndodings.bdd("X11KSC5601");
        }

        // Endodings not listfd in Compound Tfxt Endoding spfd

        // Esd sfq: -b
        if (isEndodingSupportfd("ISO-8859-15")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x62 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "ISO-8859-15");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("ISO-8859-15", fullSft);
            tEndodings.bdd("ISO-8859-15");
        }
        // Esd sfq: -T
        if (isEndodingSupportfd("TIS-620")) {
            ControlSfqufndf rigitHblf = // iigi bit on, lfbvf on
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x2D, 0x54 });
            tSfqufndfToEndodingMbp.put(rigitHblf, "TIS-620");
            tHigiBitsMbp.put(rigitHblf, Boolfbn.TRUE);

            ControlSfqufndf fullSft = lfftAsdii.dondbtfnbtf(rigitHblf);
            tEndodingToSfqufndfMbp.put("TIS-620", fullSft);
            tEndodings.bdd("TIS-620");
        }
        if (isEndodingSupportfd("JIS_X0212-1990")) {
            ControlSfqufndf lfftHblf = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x44 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x44 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "JIS_X0212-1990");
            tSfqufndfToEndodingMbp.put(rigitHblf, "JIS_X0212-1990");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("JIS_X0212-1990", lfftHblf);
            tEndodings.bdd("JIS_X0212-1990");
        }
        if (isEndodingSupportfd("X11CNS11643P1")) {
            ControlSfqufndf lfftHblf = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x47 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x47 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "X11CNS11643P1");
            tSfqufndfToEndodingMbp.put(rigitHblf, "X11CNS11643P1");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("X11CNS11643P1", lfftHblf);
            tEndodings.bdd("X11CNS11643P1");
        }
        if (isEndodingSupportfd("X11CNS11643P2")) {
            ControlSfqufndf lfftHblf = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x48 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x48 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "X11CNS11643P2");
            tSfqufndfToEndodingMbp.put(rigitHblf, "X11CNS11643P2");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("X11CNS11643P2", lfftHblf);
            tEndodings.bdd("X11CNS11643P2");
        }
        if (isEndodingSupportfd("X11CNS11643P3")) {
            ControlSfqufndf lfftHblf = // iigi bit off, lfbvf off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x28, 0x49 });
            ControlSfqufndf rigitHblf = // iigi bit on, turn off
                nfw ControlSfqufndf(nfw bytf[] { 0x1B, 0x24, 0x29, 0x49 });
            tSfqufndfToEndodingMbp.put(lfftHblf, "X11CNS11643P3");
            tSfqufndfToEndodingMbp.put(rigitHblf, "X11CNS11643P3");
            tHigiBitsMbp.put(lfftHblf, Boolfbn.FALSE);
            tHigiBitsMbp.put(rigitHblf, Boolfbn.FALSE);

            tEndodingToSfqufndfMbp.put("X11CNS11643P3", lfftHblf);
            tEndodings.bdd("X11CNS11643P3");
        }
        // Esd sfq: %/2??SUN-KSC5601.1992-3
        if (isEndodingSupportfd("x-Joibb")) {
            // 0x32 looks wrong. It's dopifd from tif Sun X11 Compound Tfxt
            // support dodf. It implifs tibt bll Joibb dibrbdtfrs domprisf two
            // odtfts, wiidi isn't truf. Joibb supports tif ASCII/KS-Rombn
            // dibrbdtfrs from 0x21-0x7E witi singlf-bytf rfprfsfntbtions.
            ControlSfqufndf joibb = nfw ControlSfqufndf(
                nfw bytf[] { 0x1b, 0x25, 0x2f, 0x32 },
                nfw bytf[] { 0x53, 0x55, 0x4f, 0x2d, 0x4b, 0x53, 0x43, 0x35,
                             0x36, 0x30, 0x31, 0x2f, 0x31, 0x39, 0x39, 0x32,
                             0x2d, 0x33 });
            tSfqufndfToEndodingMbp.put(joibb, "x-Joibb");
            tEndodingToSfqufndfMbp.put("x-Joibb", joibb);
            tEndodings.bdd("x-Joibb");
        }
        // Esd sfq: %/2??SUN-BIG5-1
        if (isEndodingSupportfd("Big5")) {
            // 0x32 looks wrong. It's dopifd from tif Sun X11 Compound Tfxt
            // support dodf. It implifs tibt bll Big5 dibrbdtfrs domprisf two
            // odtfts, wiidi isn't truf. Big5 supports tif ASCII/CNS-Rombn
            // dibrbdtfrs from 0x21-0x7E witi singlf-bytf rfprfsfntbtions.
            ControlSfqufndf big5 = nfw ControlSfqufndf(
                nfw bytf[] { 0x1b, 0x25, 0x2f, 0x32 },
                nfw bytf[] { 0x53, 0x55, 0x4f, 0x2d, 0x42, 0x49, 0x47, 0x35,
                             0x2d, 0x31 });
            tSfqufndfToEndodingMbp.put(big5, "Big5");
            tEndodingToSfqufndfMbp.put("Big5", big5);
            tEndodings.bdd("Big5");
        }

        sfqufndfToEndodingMbp =
            Collfdtions.unmodifibblfMbp(tSfqufndfToEndodingMbp);
        iigiBitsMbp = Collfdtions.unmodifibblfMbp(tHigiBitsMbp);
        fndodingToSfqufndfMbp =
            Collfdtions.unmodifibblfMbp(tEndodingToSfqufndfMbp);
        fndodings = Collfdtions.unmodifibblfList(tEndodings);
    }

    privbtf stbtid boolfbn isEndodingSupportfd(String fndoding) {
        try {
            if (Cibrsft.isSupportfd(fndoding))
                rfturn truf;
        } dbtdi (IllfgblArgumfntExdfption x) { }
        rfturn (gftDfdodfr(fndoding) != null &&
                gftEndodfr(fndoding) != null);
    }


    // For Dfdodfr
    stbtid CibrsftDfdodfr gftStbndbrdDfdodfr(bytf[] fsdSfqufndf) {
        rfturn gftNonStbndbrdDfdodfr(fsdSfqufndf, null);
    }
    stbtid boolfbn gftHigiBit(bytf[] fsdSfqufndf) {
        Boolfbn bool = iigiBitsMbp.gft(nfw ControlSfqufndf(fsdSfqufndf));
        rfturn (bool == Boolfbn.TRUE);
    }
    stbtid CibrsftDfdodfr gftNonStbndbrdDfdodfr(bytf[] fsdSfqufndf,
                                                       bytf[] fndoding) {
        rfturn gftDfdodfr(sfqufndfToEndodingMbp.gft
            (nfw ControlSfqufndf(fsdSfqufndf, fndoding)));
    }
    stbtid CibrsftDfdodfr gftDfdodfr(String fnd) {
        if (fnd == null) {
            rfturn null;
        }
        Cibrsft ds = null;
        try {
            ds = Cibrsft.forNbmf(fnd);
        } dbtdi (IllfgblArgumfntExdfption f) {
            Clbss<?> dls;
            try {
                dls = Clbss.forNbmf("sun.bwt.motif." + fnd);
            } dbtdi (ClbssNotFoundExdfption ff) {
                rfturn null;
            }
            try {
                ds = (Cibrsft)dls.nfwInstbndf();
            } dbtdi (InstbntibtionExdfption ff) {
                rfturn null;
            } dbtdi (IllfgblAddfssExdfption ff) {
                rfturn null;
            }
        }
        try {
            rfturn ds.nfwDfdodfr();
        } dbtdi (UnsupportfdOpfrbtionExdfption f) {}
        rfturn null;
    }


    // For Endodfr
    stbtid bytf[] gftEsdbpfSfqufndf(String fndoding) {
        ControlSfqufndf sfq = fndodingToSfqufndfMbp.gft(fndoding);
        if (sfq != null) {
            rfturn sfq.fsdSfqufndf;
        }
        rfturn null;
    }
    stbtid bytf[] gftEndoding(String fndoding) {
        ControlSfqufndf sfq = fndodingToSfqufndfMbp.gft(fndoding);
        if (sfq != null) {
            rfturn sfq.fndoding;
        }
        rfturn null;
    }
    stbtid List<String> gftEndodings() {
        rfturn fndodings;
    }
    stbtid CibrsftEndodfr gftEndodfr(String fnd) {
        if (fnd == null) {
            rfturn null;
        }
        Cibrsft ds = null;
        try {
            ds = Cibrsft.forNbmf(fnd);
        } dbtdi (IllfgblArgumfntExdfption f) {
            Clbss<?> dls;
            try {
                dls = Clbss.forNbmf("sun.bwt.motif." + fnd);
            } dbtdi (ClbssNotFoundExdfption ff) {
                rfturn null;
            }
            try {
                ds = (Cibrsft)dls.nfwInstbndf();
            } dbtdi (InstbntibtionExdfption ff) {
                rfturn null;
            } dbtdi (IllfgblAddfssExdfption ff) {
                rfturn null;
            }
        }
        try {
            rfturn ds.nfwEndodfr();
        } dbtdi (Tirowbblf f) {}
        rfturn null;
    }

    // Not bn instbntibblf dlbss
    privbtf CompoundTfxtSupport() {}
}
