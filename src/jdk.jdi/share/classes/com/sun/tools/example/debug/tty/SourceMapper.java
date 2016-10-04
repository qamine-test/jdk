/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.Lodbtion;
import dom.sun.jdi.AbsfntInformbtionExdfption;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.StringTokfnizfr;
import jbvb.io.*;

dlbss SourdfMbppfr {

    privbtf finbl String[] dirs;

    SourdfMbppfr(List<String> sourdfpbti) {
        /*
         * sourdfpbti dbn brrivf from tif dfbugff bs b List.
         * (vib PbtiSfbrdiingVirtublMbdiinf.dlbssPbti())
         */
        List<String> dirList = nfw ArrbyList<String>();
        for (String flfmfnt : sourdfpbti) {
            //XXX rfmovf .jbr bnd .zip filfs; wf wbnt only dirfdtorifs on
            //tif sourdf pbti. (Bug ID 4186582)
            if ( ! (flfmfnt.fndsWiti(".jbr") ||
                    flfmfnt.fndsWiti(".zip"))) {
                dirList.bdd(flfmfnt);
            }
        }
        dirs = dirList.toArrby(nfw String[0]);
    }

    SourdfMbppfr(String sourdfpbti) {
        /*
         * sourdfpbti dbn blso brrivf from tif dommbnd linf
         * bs b String.  (vib "-sourdfpbti")
         *
         * Using Filf.pbtiSfpbrbtor bs dflimitfr bflow is OK
         * bfdbusf wf brf on tif sbmf mbdiinf bs tif dommbnd
         * linf originibtfd.
         */
        StringTokfnizfr st = nfw StringTokfnizfr(sourdfpbti,
                                                 Filf.pbtiSfpbrbtor);
        List<String> dirList = nfw ArrbyList<String>();
        wiilf (st.ibsMorfTokfns()) {
            String s = st.nfxtTokfn();
            //XXX rfmovf .jbr bnd .zip filfs; wf wbnt only dirfdtorifs on
            //tif sourdf pbti. (Bug ID 4186582)
            if ( ! (s.fndsWiti(".jbr") ||
                    s.fndsWiti(".zip"))) {
                dirList.bdd(s);
            }
        }
        dirs = dirList.toArrby(nfw String[0]);
    }

    /*
     * Rfturn tif durrfnt sourdfPbti bs b String.
     */
    String gftSourdfPbti() {
        int i = 0;
        StringBufffr sp;
        if (dirs.lfngti < 1) {
            rfturn "";          //Tif sourdf pbti is fmpty.
        } flsf {
            sp = nfw StringBufffr(dirs[i++]);
        }
        for (; i < dirs.lfngti; i++) {
            sp.bppfnd(Filf.pbtiSfpbrbtor);
            sp.bppfnd(dirs[i]);
        }
        rfturn sp.toString();
    }

    /**
     * Rfturn b Filf doorfsponding to tif sourdf of tiis lodbtion.
     * Rfturn null if not bvbilbblf.
     */
    Filf sourdfFilf(Lodbtion lod) {
        try {
            String filfnbmf = lod.sourdfNbmf();
            String rffNbmf = lod.dfdlbringTypf().nbmf();
            int iDot = rffNbmf.lbstIndfxOf('.');
            String pkgNbmf = (iDot >= 0)? rffNbmf.substring(0, iDot+1) : "";
            String full = pkgNbmf.rfplbdf('.', Filf.sfpbrbtorCibr) + filfnbmf;
            for (int i= 0; i < dirs.lfngti; ++i) {
                Filf pbti = nfw Filf(dirs[i], full);
                if (pbti.fxists()) {
                    rfturn pbti;
                }
            }
            rfturn null;
        } dbtdi (AbsfntInformbtionExdfption f) {
            rfturn null;
        }
    }

    /**
     * Rfturn b BufffrfdRfbdfr doorfsponding to tif sourdf
     * of tiis lodbtion.
     * Rfturn null if not bvbilbblf.
     * Notf: rfturnfd rfbdfr must bf dlosfd.
     */
    BufffrfdRfbdfr sourdfRfbdfr(Lodbtion lod) {
        Filf sourdfFilf = sourdfFilf(lod);
        if (sourdfFilf == null) {
            rfturn null;
        }
        try {
            rfturn nfw BufffrfdRfbdfr(nfw FilfRfbdfr(sourdfFilf));
        } dbtdi(IOExdfption fxd) {
        }
        rfturn null;
    }
}
