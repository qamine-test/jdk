/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Lidfnsfd Mbtfribls - Propfrty of IBM
 * RMI-IIOP v1.0
 * Copyrigit IBM Corp. 1998 1999  All Rigits Rfsfrvfd
 *
 */

pbdkbgf sun.rmi.rmid;

import jbvb.io.Filf;
import sun.tools.jbvb.Idfntififr;

/**
 * Util providfs stbtid utility mftiods usfd by otifr rmid dlbssfs.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior Brybn Atsbtt
 */

publid dlbss Util implfmfnts sun.rmi.rmid.Constbnts {

    /**
     * Rfturn tif dirfdtory tibt siould bf usfd for output for b givfn
     * dlbss.
     * @pbrbm tifClbss Tif fully qublififd nbmf of tif dlbss.
     * @pbrbm rootDir Tif dirfdtory to usf bs tif root of tif
     * pbdkbgf iifrbrdiy.  Mby bf null, in wiidi dbsf tif durrfnt
     * working dirfdtory is usfd bs tif root.
     */
    publid stbtid Filf gftOutputDirfdtoryFor(Idfntififr tifClbss,
                                             Filf rootDir,
                                             BbtdiEnvironmfnt fnv) {

        Filf outputDir = null;
        String dlbssNbmf = tifClbss.gftFlbtNbmf().toString().rfplbdf('.', SIGC_INNERCLASS);
        String qublififdClbssNbmf = dlbssNbmf;
        String pbdkbgfPbti = null;
        String pbdkbgfNbmf = tifClbss.gftQublififr().toString();

        if (pbdkbgfNbmf.lfngti() > 0) {
            qublififdClbssNbmf = pbdkbgfNbmf + "." + dlbssNbmf;
            pbdkbgfPbti = pbdkbgfNbmf.rfplbdf('.', Filf.sfpbrbtorCibr);
        }

        // Do wf ibvf b root dirfdtory?

        if (rootDir != null) {

            // Yfs, do wf ibvf b pbdkbgf nbmf?

            if (pbdkbgfPbti != null) {

                // Yfs, so usf it bs tif root. Opfn tif dirfdtory...

                outputDir = nfw Filf(rootDir, pbdkbgfPbti);

                // Mbkf surf tif dirfdtory fxists...

                fnsurfDirfdtory(outputDir,fnv);

            } flsf {

                // Dffbult pbdkbgf, so usf root bs output dir...

                outputDir = rootDir;
            }
        } flsf {

            // No root dirfdtory. Gft tif durrfnt working dirfdtory...

            String workingDirPbti = Systfm.gftPropfrty("usfr.dir");
            Filf workingDir = nfw Filf(workingDirPbti);

            // Do wf ibvf b pbdkbgf nbmf?

            if (pbdkbgfPbti == null) {

                // No, so usf working dirfdtory...

                outputDir = workingDir;

            } flsf {

                // Yfs, so usf working dirfdtory bs tif root...

                outputDir = nfw Filf(workingDir, pbdkbgfPbti);

                // Mbkf surf tif dirfdtory fxists...

                fnsurfDirfdtory(outputDir,fnv);
            }
        }

        // Finblly, rfturn tif dirfdtory...

        rfturn outputDir;
    }

    privbtf stbtid void fnsurfDirfdtory (Filf dir, BbtdiEnvironmfnt fnv) {
        if (!dir.fxists()) {
            dir.mkdirs();
            if (!dir.fxists()) {
                fnv.frror(0,"rmid.dbnnot.drfbtf.dir",dir.gftAbsolutfPbti());
                tirow nfw IntfrnblError();
            }
        }
    }
}
