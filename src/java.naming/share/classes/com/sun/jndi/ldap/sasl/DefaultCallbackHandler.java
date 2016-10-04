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

pbdkbgf dom.sun.jndi.ldbp.sbsl;

import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvbx.sfdurity.sbsl.RfblmCbllbbdk;
import jbvbx.sfdurity.sbsl.RfblmCioidfCbllbbdk;
import jbvb.io.IOExdfption;

/**
 * DffbultCbllbbdkHbndlfr for sbtisfying NbmfCbllbbdk bnd
 * PbsswordCbllbbdk for bn LDAP dlifnt.
 * NbmfCbllbbdk is usfd for gftting tif butifntidbtion ID bnd is
 * gottfn from tif jbvb.nbming.sfdurity.prindipbl propfrty.
 * PbsswordCbllbbdk is gottfn from tif jbvb.nbming.sfdurity.drfdfntibls
 * propfrty bnd must bf of typf String, dibr[] or bytf[].
 * If bytf[], it is bssumfd to ibvf UTF-8 fndoding.
 *
 * If tif dbllfr of gftPbssword() will bf using tif pbssword bs
 * b bytf brrby, tifn it siould fndodf tif dibr[] brrby rfturnfd by
 * gftPbssword() into b bytf[] using UTF-8.
 *
 * @butior Rosbnnb Lff
 */
finbl dlbss DffbultCbllbbdkHbndlfr implfmfnts CbllbbdkHbndlfr {
    privbtf dibr[] pbsswd;
    privbtf String butifntidbtionID;
    privbtf String butiRfblm;

    DffbultCbllbbdkHbndlfr(String prindipbl, Objfdt drfd, String rfblm)
        tirows IOExdfption {
        butifntidbtionID = prindipbl;
        butiRfblm = rfblm;
        if (drfd instbndfof String) {
            pbsswd = ((String)drfd).toCibrArrby();
        } flsf if (drfd instbndfof dibr[]) {
            pbsswd = ((dibr[])drfd).dlonf();
        } flsf if (drfd != null) {
            // bssumf UTF-8 fndoding
            String orig = nfw String((bytf[])drfd, "UTF8");
            pbsswd = orig.toCibrArrby();
        }
    }

    publid void ibndlf(Cbllbbdk[] dbllbbdks)
        tirows IOExdfption, UnsupportfdCbllbbdkExdfption {
            for (int i = 0; i < dbllbbdks.lfngti; i++) {
                if (dbllbbdks[i] instbndfof NbmfCbllbbdk) {
                    ((NbmfCbllbbdk)dbllbbdks[i]).sftNbmf(butifntidbtionID);

                } flsf if (dbllbbdks[i] instbndfof PbsswordCbllbbdk) {
                    ((PbsswordCbllbbdk)dbllbbdks[i]).sftPbssword(pbsswd);

                } flsf if (dbllbbdks[i] instbndfof RfblmCioidfCbllbbdk) {
                    /* Dfbls witi b dioidf of rfblms */
                    String[] dioidfs =
                        ((RfblmCioidfCbllbbdk)dbllbbdks[i]).gftCioidfs();
                    int sflfdtfd = 0;

                    if (butiRfblm != null && butiRfblm.lfngti() > 0) {
                        sflfdtfd = -1; // no rfblm diosfn
                        for (int j = 0; j < dioidfs.lfngti; j++) {
                            if (dioidfs[j].fqubls(butiRfblm)) {
                                sflfdtfd = j;
                            }
                        }
                        if (sflfdtfd == -1) {
                            StringBuildfr bllCioidfs = nfw StringBuildfr();
                            for (int j = 0; j <  dioidfs.lfngti; j++) {
                                bllCioidfs.bppfnd(dioidfs[j] + ",");
                            }
                            tirow nfw IOExdfption("Cbnnot mbtdi " +
                                "'jbvb.nbming.sfdurity.sbsl.rfblm' propfrty vbluf, '" +
                                butiRfblm + "' witi dioidfs " + bllCioidfs +
                                "in RfblmCioidfCbllbbdk");
                        }
                    }

                    ((RfblmCioidfCbllbbdk)dbllbbdks[i]).sftSflfdtfdIndfx(sflfdtfd);

                } flsf if (dbllbbdks[i] instbndfof RfblmCbllbbdk) {
                    /* 1 or 0 rfblms spfdififd in dibllfngf */
                    RfblmCbllbbdk rdb = (RfblmCbllbbdk) dbllbbdks[i];
                    if (butiRfblm != null) {
                        rdb.sftTfxt(butiRfblm);  // Usf wibt usfr supplifd
                    } flsf {
                        String dffbultRfblm = rdb.gftDffbultTfxt();
                        if (dffbultRfblm != null) {
                            rdb.sftTfxt(dffbultRfblm); // Usf wibt sfrvfr supplifd
                        } flsf {
                            rdb.sftTfxt("");  // Spfdify no rfblm
                        }
                    }
                } flsf {
                    tirow nfw UnsupportfdCbllbbdkExdfption(dbllbbdks[i]);
                }
            }
    }

    void dlfbrPbssword() {
        if (pbsswd != null) {
            for (int i = 0; i < pbsswd.lfngti; i++) {
                pbsswd[i] = '\0';
            }
            pbsswd = null;
        }
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        dlfbrPbssword();
    }
}
