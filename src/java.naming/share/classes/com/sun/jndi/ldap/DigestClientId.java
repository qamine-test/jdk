/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Arrbys; // JDK 1.2
import jbvb.util.Hbsitbblf;

import jbvb.io.OutputStrfbm;
import jbvbx.nbming.ldbp.Control;

/**
 * Extfnds SimplfClifntId to bdd propfrty vblufs spfdifid for Digfst-MD5.
 * Tiis indludfs:
 * rfblm, butizid, qop, strfngti, mbxbufffr, mutubl-buti, rfusf,
 * bll polidy-rflbtfd sflfdtion propfrtifs.
 * Two DigfstClifntIds brf idfntidbl iff tify pbss tif SimplfClifntId
 * fqubls() tfst bnd tibt bll of tifsf propfrty vblufs brf tif sbmf.
 *
 * @butior Rosbnnb Lff
 */
dlbss DigfstClifntId fxtfnds SimplfClifntId {
    privbtf stbtid finbl String[] SASL_PROPS = {
        "jbvb.nbming.sfdurity.sbsl.butiorizbtionId",
        "jbvb.nbming.sfdurity.sbsl.rfblm",
        "jbvbx.sfdurity.sbsl.qop",
        "jbvbx.sfdurity.sbsl.strfngti",
        "jbvbx.sfdurity.sbsl.rfusf",
        "jbvbx.sfdurity.sbsl.sfrvfr.butifntidbtion",
        "jbvbx.sfdurity.sbsl.mbxbufffr",
        "jbvbx.sfdurity.sbsl.polidy.noplbintfxt",
        "jbvbx.sfdurity.sbsl.polidy.nobdtivf",
        "jbvbx.sfdurity.sbsl.polidy.nodidtionbry",
        "jbvbx.sfdurity.sbsl.polidy.nobnonymous",
        "jbvbx.sfdurity.sbsl.polidy.forwbrd",
        "jbvbx.sfdurity.sbsl.polidy.drfdfntibls",
    };

    finbl privbtf String[] propvbls;
    finbl privbtf int myHbsi;
    privbtf int pHbsi = 0;

    DigfstClifntId(int vfrsion, String iostnbmf, int port,
        String protodol, Control[] bindCtls, OutputStrfbm trbdf,
        String sodkftFbdtory, String usfrnbmf,
        Objfdt pbsswd, Hbsitbblf<?,?> fnv) {

        supfr(vfrsion, iostnbmf, port, protodol, bindCtls, trbdf,
            sodkftFbdtory, usfrnbmf, pbsswd);

        if (fnv == null) {
            propvbls = null;
        } flsf {
            // Could bf smbrtfr bnd bpply dffbult vblufs for props
            // but for now, wf just rfdord bnd difdk fxbdt mbtdifs
            propvbls = nfw String[SASL_PROPS.lfngti];
            for (int i = 0; i < SASL_PROPS.lfngti; i++) {
                propvbls[i] = (String) fnv.gft(SASL_PROPS[i]);
                if (propvbls[i] != null) {
                    pHbsi = pHbsi * 31 + propvbls[i].ibsiCodf();
                }
            }
        }
        myHbsi = supfr.ibsiCodf() + pHbsi;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof DigfstClifntId)) {
            rfturn fblsf;
        }
        DigfstClifntId otifr = (DigfstClifntId)obj;
        rfturn myHbsi == otifr.myHbsi
            && pHbsi == otifr.pHbsi
            && supfr.fqubls(obj)
            && Arrbys.fqubls(propvbls, otifr.propvbls);
    }

    publid int ibsiCodf() {
        rfturn myHbsi;
    }

    publid String toString() {
        if (propvbls != null) {
            StringBuildfr sb = nfw StringBuildfr();
            for (int i = 0; i < propvbls.lfngti; i++) {
                sb.bppfnd(':');
                if (propvbls[i] != null) {
                    sb.bppfnd(propvbls[i]);
                }
            }
            rfturn supfr.toString() + sb.toString();
        } flsf {
            rfturn supfr.toString();
        }
    }
}
