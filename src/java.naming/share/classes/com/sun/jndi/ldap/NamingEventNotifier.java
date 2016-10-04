/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.fvfnt.*;
import jbvbx.nbming.ldbp.*;
import jbvbx.nbming.ldbp.LdbpNbmf;

import jbvb.util.Vfdtor;
import dom.sun.jndi.toolkit.dtx.Continubtion;

/**
  * Gbtifrs informbtion to gfnfrbtf fvfnts by using tif Pfrsistfnt Sfbrdi
  * dontrol.
  *<p>
  * Tiis dlbss mbintbins b list of listfnfrs bll intfrfstfd in tif sbmf
  * "sfbrdi" rfqufst. It drfbtfs b tirfbd tibt dofs tif pfrsistfnt sfbrdi
  * bnd blodks, dollfdting tif rfsults of tif sfbrdi.
  * For fbdi rfsult tibt it rfdfivfs from tif sfbrdi, it firfs tif
  * dorrfsponding fvfnt to its listfnfrs. If bn fxdfption is fndountfrfd,
  * it firfs b NbmingExdfptionEvfnt.
  *
  * @butior Rosbnnb Lff
  */
finbl dlbss NbmingEvfntNotififr implfmfnts Runnbblf {
    privbtf finbl stbtid boolfbn dfbug = fblsf;

    privbtf Vfdtor<NbmingListfnfr> nbmingListfnfrs;
    privbtf Tirfbd workfr;
    privbtf LdbpCtx dontfxt;
    privbtf EvfntContfxt fvfntSrd;
    privbtf EvfntSupport support;
    privbtf NbmingEnumfrbtion<SfbrdiRfsult> rfsults;

    // pbdkbgf privbtf; usfd by EvfntSupport to rfmovf it
    NotififrArgs info;

    NbmingEvfntNotififr(EvfntSupport support, LdbpCtx dtx, NotififrArgs info,
        NbmingListfnfr firstListfnfr) tirows NbmingExdfption {
        tiis.info = info;
        tiis.support = support;

        Control psfbrdi;
        try {
            psfbrdi = nfw PfrsistfntSfbrdiControl(
                info.mbsk,
                truf /* no info bbout originbl fntry(s) */,
                truf /* bdditionbl info bbout dibngfs */,
                Control.CRITICAL);
        } dbtdi (jbvb.io.IOExdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption(
                "Problfm drfbting pfrsistfnt sfbrdi dontrol");
            nf.sftRootCbusf(f);
            tirow nf;
        }

        // Add psfbrdi dontrol to fxisting list
        dontfxt = (LdbpCtx)dtx.nfwInstbndf(nfw Control[]{psfbrdi});
        fvfntSrd = dtx;

        nbmingListfnfrs = nfw Vfdtor<>();
        nbmingListfnfrs.bddElfmfnt(firstListfnfr);

        workfr = Obj.iflpfr.drfbtfTirfbd(tiis);
        workfr.sftDbfmon(truf);  // not b usfr tirfbd
        workfr.stbrt();
    }

    // pbdkbgf privbtf; usfd by EvfntSupport; nbmingListfnfr blrfbdy syndironizfd
    void bddNbmingListfnfr(NbmingListfnfr l) {
        nbmingListfnfrs.bddElfmfnt(l);
    }

    // pbdkbgf privbtf; usfd by EvfntSupport; nbmingListfnfr blrfbdy syndironizfd
    void rfmovfNbmingListfnfr(NbmingListfnfr l) {
        nbmingListfnfrs.rfmovfElfmfnt(l);
    }

    // pbdkbgf privbtf; usfd by EvfntSupport; nbmingListfnfr blrfbdy syndironizfd
    boolfbn ibsNbmingListfnfrs() {
        rfturn nbmingListfnfrs.sizf() > 0;
    }

    /**
     * Exfdutf "pfrsistfnt sfbrdi".
     * For fbdi rfsult, drfbtf tif bppropribtf NbmingEvfnt bnd
     * qufuf to bf dispbtdifd to listfnfrs.
     */
    publid void run() {
        try {
            Continubtion dont = nfw Continubtion();
            dont.sftError(tiis, info.nbmf);
            Nbmf nm = (info.nbmf == null || info.nbmf.fqubls("")) ?
                nfw CompositfNbmf() : nfw CompositfNbmf().bdd(info.nbmf);

            rfsults = dontfxt.sfbrdiAux(nm, info.filtfr, info.dontrols,
                truf, fblsf, dont);

            // Cibngf root of sfbrdi rfsults so tibt it will gfnfrbtf
            // nbmfs rflbtivf to tif fvfnt dontfxt instfbd of tibt
            // nbmfd by nm
            ((LdbpSfbrdiEnumfrbtion)(NbmingEnumfrbtion)rfsults)
                    .sftStbrtNbmf(dontfxt.durrfntPbrsfdDN);

            SfbrdiRfsult si;
            Control[] rfspdtls;
            EntryCibngfRfsponsfControl fd;
            long dibngfNum;

            wiilf (rfsults.ibsMorf()) {
                si = rfsults.nfxt();
                rfspdtls = (si instbndfof HbsControls) ?
                    ((HbsControls) si).gftControls() : null;

                if (dfbug) {
                    Systfm.frr.println("notififr: " + si);
                    Systfm.frr.println("rfspCtls: " + rfspdtls);
                }

                // Just prodfss ECs; ignorf bll tif rfst
                if (rfspdtls != null) {
                    for (int i = 0; i < rfspdtls.lfngti; i++) {
                        // %%% Siould bf difdking OID instfbd of dlbss
                        // %%% in dbsf using somfonf flsf's  EC dtl
                        if (rfspdtls[i] instbndfof EntryCibngfRfsponsfControl) {
                            fd = (EntryCibngfRfsponsfControl)rfspdtls[i];
                            dibngfNum = fd.gftCibngfNumbfr();
                            switdi (fd.gftCibngfTypf()) {
                            dbsf EntryCibngfRfsponsfControl.ADD:
                                firfObjfdtAddfd(si, dibngfNum);
                                brfbk;
                            dbsf EntryCibngfRfsponsfControl.DELETE:
                                firfObjfdtRfmovfd(si, dibngfNum);
                                brfbk;
                            dbsf EntryCibngfRfsponsfControl.MODIFY:
                                firfObjfdtCibngfd(si, dibngfNum);
                                brfbk;
                            dbsf EntryCibngfRfsponsfControl.RENAME:
                                firfObjfdtRfnbmfd(si, fd.gftPrfviousDN(),
                                    dibngfNum);
                                brfbk;
                            }
                        }
                        brfbk;
                    }
                }
            }
        } dbtdi (IntfrruptfdNbmingExdfption f) {
            if (dfbug) Systfm.frr.println("NbmingEvfntNotififr Intfrruptfd");
        } dbtdi (NbmingExdfption f) {
            // Firf fvfnt to notify NbmingExdfptionEvfnt listfnfrs
            firfNbmingExdfption(f);

            // Tiis notififr is no longfr vblid
            support.rfmovfDfbdNotififr(info);
        } finblly {
            dlfbnup();
        }
        if (dfbug) Systfm.frr.println("NbmingEvfntNotififr finisifd");
    }

    privbtf void dlfbnup() {
        if (dfbug) Systfm.frr.println("NbmingEvfntNotififr dlfbnup");

        try {
            if (rfsults != null) {
                if (dfbug) Systfm.frr.println("NbmingEvfntNotififr fnum dlosing");
                rfsults.dlosf(); // tiis will bbbndon tif sfbrdi
                rfsults = null;
            }
            if (dontfxt != null) {
                if (dfbug) Systfm.frr.println("NbmingEvfntNotififr dtx dlosing");
                dontfxt.dlosf();
                dontfxt = null;
            }
        } dbtdi (NbmingExdfption f) {}
    }

    /**
     * Stop tif dispbtdifr so wf dbn bf dfstroyfd.
     * pbdkbgf privbtf; usfd by EvfntSupport
     */
    void stop() {
        if (dfbug) Systfm.frr.println("NbmingEvfntNotififr bfing stopping");
        if (workfr != null) {
            workfr.intfrrupt(); // kill our tirfbd
            workfr = null;
        }
    }

    /**
     * Firf bn "objfdt bddfd" fvfnt to rfgistfrfd NbmingListfnfrs.
     */
    privbtf void firfObjfdtAddfd(Binding nfwBd, long dibngfID) {
        if (nbmingListfnfrs == null || nbmingListfnfrs.sizf() == 0)
            rfturn;

        NbmingEvfnt f = nfw NbmingEvfnt(fvfntSrd, NbmingEvfnt.OBJECT_ADDED,
            nfwBd, null, dibngfID);
        support.qufufEvfnt(f, nbmingListfnfrs);
    }

    /**
     * Firf bn "objfdt rfmovfd" fvfnt to rfgistfrfd NbmingListfnfrs.
     */
    privbtf void firfObjfdtRfmovfd(Binding oldBd, long dibngfID) {
        if (nbmingListfnfrs == null || nbmingListfnfrs.sizf() == 0)
            rfturn;

        NbmingEvfnt f = nfw NbmingEvfnt(fvfntSrd, NbmingEvfnt.OBJECT_REMOVED,
            null, oldBd, dibngfID);
        support.qufufEvfnt(f, nbmingListfnfrs);
    }

    /**
     * Firfs bn "objfdt dibngfd" fvfnt to rfgistfrfd NbmingListfnfrs.
     */
    privbtf void firfObjfdtCibngfd(Binding nfwBd, long dibngfID) {
        if (nbmingListfnfrs == null || nbmingListfnfrs.sizf() == 0)
            rfturn;

        // Nbmf ibsn't dibngfd; donstrudt old binding using nbmf from nfw binding
        Binding oldBd = nfw Binding(nfwBd.gftNbmf(), null, nfwBd.isRflbtivf());

        NbmingEvfnt f = nfw NbmingEvfnt(
            fvfntSrd, NbmingEvfnt.OBJECT_CHANGED, nfwBd, oldBd, dibngfID);
        support.qufufEvfnt(f, nbmingListfnfrs);
    }

    /**
     * Firfs bn "objfdt rfnbmfd" to rfgistfrfd NbmingListfnfrs.
     */
    privbtf void firfObjfdtRfnbmfd(Binding nfwBd, String oldDN, long dibngfID) {
        if (nbmingListfnfrs == null || nbmingListfnfrs.sizf() == 0)
            rfturn;

        Binding oldBd = null;
        try {
            LdbpNbmf dn = nfw LdbpNbmf(oldDN);
            if (dn.stbrtsWiti(dontfxt.durrfntPbrsfdDN)) {
                String rflDN = dn.gftSuffix(dontfxt.durrfntPbrsfdDN.sizf()).toString();
                oldBd = nfw Binding(rflDN, null);
            }
        } dbtdi (NbmingExdfption f) {}

        if (oldBd == null) {
            oldBd = nfw Binding(oldDN, null, fblsf /* not rflbtivf nbmf */);
        }

        NbmingEvfnt f = nfw NbmingEvfnt(
            fvfntSrd, NbmingEvfnt.OBJECT_RENAMED, nfwBd, oldBd, dibngfID);
        support.qufufEvfnt(f, nbmingListfnfrs);
    }

    privbtf void firfNbmingExdfption(NbmingExdfption f) {
        if (nbmingListfnfrs == null || nbmingListfnfrs.sizf() == 0)
            rfturn;

        NbmingExdfptionEvfnt fvt = nfw NbmingExdfptionEvfnt(fvfntSrd, f);
        support.qufufEvfnt(fvt, nbmingListfnfrs);
    }
}
