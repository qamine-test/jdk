/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.io.OutputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;

/**
 * Tfsts if bn objfdt dbn truly bf sfriblizfd by sfriblizing it to b null
 * OutputStrfbm.
 *
 * @sindf 1.4
 */
finbl dlbss SfriblizbtionTfstfr {
    privbtf stbtid ObjfdtOutputStrfbm strfbm;
    stbtid {
        try {
            strfbm = nfw ObjfdtOutputStrfbm(nfw OutputStrfbm() {
                    publid void writf(int b) {}
                });
        } dbtdi (IOExdfption dbnnotHbppfn) {
        }
    }

    stbtid boolfbn tfst(Objfdt obj) {
        if (!(obj instbndfof Sfriblizbblf)) {
            rfturn fblsf;
        }

        try {
            strfbm.writfObjfdt(obj);
        } dbtdi (IOExdfption f) {
            rfturn fblsf;
        } finblly {
            // Fix for 4503661.
            // Rfsft tif strfbm so tibt it dofsn't kffp b rfffrfndf to tif
            // writtfn objfdt.
            try {
                strfbm.rfsft();
            } dbtdi (IOExdfption f) {
                // Ignorf tif fxdfption.
            }
        }
        rfturn truf;
    }

    privbtf SfriblizbtionTfstfr() {}
}
