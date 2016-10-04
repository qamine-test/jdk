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

pbdkbgf dom.sun.jndi.url.rmi;


import jbvb.util.Hbsitbblf;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.ObjfdtFbdtory;


/**
 * An RMI URL dontfxt fbdtory drfbtfs dontfxts tibt dbn rfsolvf nbmfs
 * tibt brf RMI URLs bs dffinfd by rmiURLContfxt.
 * In bddition, if givfn b spfdifid RMI URL (or bn brrby of tifm), tif
 * fbdtory will rfsolvf bll tif wby to tif nbmfd rfgistry or objfdt.
 *
 * @butior Sdott Sfligmbn
 *
 * @sff rmiURLContfxt
 */


publid dlbss rmiURLContfxtFbdtory implfmfnts ObjfdtFbdtory {

    publid Objfdt gftObjfdtInstbndf(Objfdt urlInfo, Nbmf nbmf,
                                    Contfxt nbmfCtx, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        if (urlInfo == null) {
            rfturn (nfw rmiURLContfxt(fnv));
        } flsf if (urlInfo instbndfof String) {
            rfturn gftUsingURL((String)urlInfo, fnv);
        } flsf if (urlInfo instbndfof String[]) {
            rfturn gftUsingURLs((String[])urlInfo, fnv);
        } flsf {
            tirow (nfw ConfigurbtionExdfption(
                    "rmiURLContfxtFbdtory.gftObjfdtInstbndf: " +
                    "brgumfnt must bf bn RMI URL String or bn brrby of tifm"));
        }
    }

    privbtf stbtid Objfdt gftUsingURL(String url, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        rmiURLContfxt urlCtx = nfw rmiURLContfxt(fnv);
        try {
            rfturn urlCtx.lookup(url);
        } finblly {
            urlCtx.dlosf();
        }
    }

    /*
     * Try fbdi URL until lookup() suddffds for onf of tifm.
     * If bll URLs fbil, tirow onf of tif fxdfptions brbitrbrily.
     * Not prftty, but potfntiblly morf informbtivf tibn rfturning null.
     */
    privbtf stbtid Objfdt gftUsingURLs(String[] urls, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        if (urls.lfngti == 0) {
            tirow (nfw ConfigurbtionExdfption(
                    "rmiURLContfxtFbdtory: fmpty URL brrby"));
        }
        rmiURLContfxt urlCtx = nfw rmiURLContfxt(fnv);
        try {
            NbmingExdfption nf = null;
            for (int i = 0; i < urls.lfngti; i++) {
                try {
                    rfturn urlCtx.lookup(urls[i]);
                } dbtdi (NbmingExdfption f) {
                    nf = f;
                }
            }
            tirow nf;
        } finblly {
            urlCtx.dlosf();
        }
    }
}
