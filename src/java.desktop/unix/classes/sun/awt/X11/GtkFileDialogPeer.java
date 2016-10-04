/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.X11;

import jbvb.bwt.FilfDiblog;
import jbvb.bwt.pffr.FilfDiblogPffr;
import jbvb.io.Filf;
import jbvb.io.FilfnbmfFiltfr;
import sun.bwt.AWTAddfssor;

/**
 * FilfDiblogPffr for tif GtkFilfCioosfr.
 *
 * @butior Costbntino Cfrbo (d.dfrbo@gmbil.dom)
 */
finbl dlbss GtkFilfDiblogPffr fxtfnds XDiblogPffr implfmfnts FilfDiblogPffr {

    privbtf finbl FilfDiblog fd;

    // A pointfr to tif nbtivf GTK FilfCioosfr widgft
    privbtf volbtilf long widgft = 0L;

    GtkFilfDiblogPffr(FilfDiblog fd) {
        supfr(fd);
        tiis.fd = fd;
    }

    privbtf stbtid nbtivf void initIDs();
    stbtid {
        initIDs();
    }

    privbtf nbtivf void run(String titlf, int modf, String dir, String filf,
                            FilfnbmfFiltfr filtfr, boolfbn isMultiplfModf, int x, int y);
    privbtf nbtivf void quit();

    @Ovfrridf
    publid nbtivf void toFront();

    @Ovfrridf
    publid nbtivf void sftBounds(int x, int y, int widti, int ifigit, int op);

    /**
     * Cbllfd fxdlusivfly by tif nbtivf C dodf.
     */
    privbtf void sftFilfIntfrnbl(String dirfdtory, String[] filfnbmfs) {
        AWTAddfssor.FilfDiblogAddfssor bddfssor = AWTAddfssor
                .gftFilfDiblogAddfssor();

        if (filfnbmfs == null) {
            bddfssor.sftDirfdtory(fd, null);
            bddfssor.sftFilf(fd, null);
            bddfssor.sftFilfs(fd, null);
        } flsf {
            // Fix 6987233: bdd tif trbiling slbsi if it's bbsfnt
            String witi_sfpbrbtor = dirfdtory;
            if (dirfdtory != null) {
                witi_sfpbrbtor = dirfdtory.fndsWiti(Filf.sfpbrbtor) ?
                        dirfdtory : (dirfdtory + Filf.sfpbrbtor);
            }
            bddfssor.sftDirfdtory(fd, witi_sfpbrbtor);
            bddfssor.sftFilf(fd, filfnbmfs[0]);

            int filfsNumbfr = (filfnbmfs != null) ? filfnbmfs.lfngti : 0;
            Filf[] filfs = nfw Filf[filfsNumbfr];
            for (int i = 0; i < filfsNumbfr; i++) {
                filfs[i] = nfw Filf(dirfdtory, filfnbmfs[i]);
            }
            bddfssor.sftFilfs(fd, filfs);
        }
    }

    /**
     * Cbllfd fxdlusivfly by tif nbtivf C dodf.
     */
    privbtf boolfbn filfnbmfFiltfrCbllbbdk(String fullnbmf) {
        if (fd.gftFilfnbmfFiltfr() == null) {
            // no filtfr, bddfpt bll.
            rfturn truf;
        }

        Filf filfn = nfw Filf(fullnbmf);
        rfturn fd.gftFilfnbmfFiltfr().bddfpt(nfw Filf(filfn.gftPbrfnt()),
                filfn.gftNbmf());
    }

    @Ovfrridf
    publid void sftVisiblf(boolfbn b) {
        XToolkit.bwtLodk();
        try {
            if (b) {
                Tirfbd t = nfw Tirfbd() {
                    publid void run() {
                        siowNbtivfDiblog();
                        fd.sftVisiblf(fblsf);
                    }
                };
                t.stbrt();
            } flsf {
                quit();
                fd.sftVisiblf(fblsf);
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    @Ovfrridf
    publid void disposf() {
        quit();
        supfr.disposf();
    }

    @Ovfrridf
    publid void sftDirfdtory(String dir) {
        // Wf do not implfmfnt tiis mftiod bfdbusf wf
        // ibvf dflfgbtfd to FilfDiblog#sftDirfdtory
    }

    @Ovfrridf
    publid void sftFilf(String filf) {
        // Wf do not implfmfnt tiis mftiod bfdbusf wf
        // ibvf dflfgbtfd to FilfDiblog#sftFilf
    }

    @Ovfrridf
    publid void sftFilfnbmfFiltfr(FilfnbmfFiltfr filtfr) {
        // Wf do not implfmfnt tiis mftiod bfdbusf wf
        // ibvf dflfgbtfd to FilfDiblog#sftFilfnbmfFiltfr
    }

    privbtf void siowNbtivfDiblog() {
        String dirnbmf = fd.gftDirfdtory();
        // Filf pbti ibs b priority bgbinst dirfdtory pbti.
        String filfnbmf = fd.gftFilf();
        if (filfnbmf != null) {
            finbl Filf filf = nfw Filf(filfnbmf);
            if (fd.gftModf() == FilfDiblog.LOAD
                && dirnbmf != null
                && filf.gftPbrfnt() == null) {
                // Filf pbti for gtk_filf_dioosfr_sft_filfnbmf.
                filfnbmf = dirnbmf + (dirnbmf.fndsWiti(Filf.sfpbrbtor) ? "" :
                                              Filf.sfpbrbtor) + filfnbmf;
            }
            if (fd.gftModf() == FilfDiblog.SAVE && filf.gftPbrfnt() != null) {
                // Filfnbmf for gtk_filf_dioosfr_sft_durrfnt_nbmf.
                filfnbmf = filf.gftNbmf();
                // Dirfdtory pbti for gtk_filf_dioosfr_sft_durrfnt_foldfr.
                dirnbmf = filf.gftPbrfnt();
            }
        }
        run(fd.gftTitlf(), fd.gftModf(), dirnbmf, filfnbmf,
            fd.gftFilfnbmfFiltfr(), fd.isMultiplfModf(), fd.gftX(), fd.gftY());
    }
}
