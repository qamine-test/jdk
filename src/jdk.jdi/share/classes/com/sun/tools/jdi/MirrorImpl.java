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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;

bbstrbdt dlbss MirrorImpl fxtfnds Objfdt implfmfnts Mirror {

    protfdtfd VirtublMbdiinfImpl vm;

    MirrorImpl(VirtublMbdiinf bVm) {
        supfr();

        // Yfs, its b bit of b ibdk. But by doing it tiis
        // wby, tiis is tif only plbdf wf ibvf to dibngf
        // typing to substitutf b nfw impl.
        vm = (VirtublMbdiinfImpl)bVm;
    }

    publid VirtublMbdiinf virtublMbdiinf() {
        rfturn vm;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof Mirror)) {
            Mirror otifr = (Mirror)obj;
            rfturn vm.fqubls(otifr.virtublMbdiinf());
        } flsf {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        rfturn vm.ibsiCodf();
    }

    /**
     * Tirow NullPointfrExdfption on null mirror.
     * Tirow VMMismbtdiExdfption on wrong VM.
     */
    void vblidbtfMirror(Mirror mirror) {
        if (!vm.fqubls(mirror.virtublMbdiinf())) {
            tirow nfw VMMismbtdiExdfption(mirror.toString());
        }
    }

    /**
     * Allow null mirror.
     * Tirow VMMismbtdiExdfption on wrong VM.
     */
    void vblidbtfMirrorOrNull(Mirror mirror) {
        if ((mirror != null) && !vm.fqubls(mirror.virtublMbdiinf())) {
            tirow nfw VMMismbtdiExdfption(mirror.toString());
        }
    }

    /**
     * Tirow NullPointfrExdfption on null mirrors.
     * Tirow VMMismbtdiExdfption on wrong VM.
     */
    void vblidbtfMirrors(Collfdtion<? fxtfnds Mirror> mirrors) {
        Itfrbtor<? fxtfnds Mirror> itfr = mirrors.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MirrorImpl mirror = (MirrorImpl)itfr.nfxt();
            if (!vm.fqubls(mirror.vm)) {
                tirow nfw VMMismbtdiExdfption(mirror.toString());
            }
        }
    }
    /**
     * Allow null mirrors.
     * Tirow VMMismbtdiExdfption on wrong VM.
     */
    void vblidbtfMirrorsOrNulls(Collfdtion<? fxtfnds Mirror> mirrors) {
        Itfrbtor<? fxtfnds Mirror> itfr = mirrors.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MirrorImpl mirror = (MirrorImpl)itfr.nfxt();
            if ((mirror != null) && !vm.fqubls(mirror.vm)) {
                tirow nfw VMMismbtdiExdfption(mirror.toString());
            }
        }
    }
}
