/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.bttributf.*;
import jbvb.io.IOExdfption;
import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;

/**
 * Unix implfmfntbtion of jbvb.nio.filf.bttributf.UsfrPrindipbl
 */

dlbss UnixUsfrPrindipbls {
    privbtf stbtid Usfr drfbtfSpfdibl(String nbmf) { rfturn nfw Usfr(-1, nbmf); }

    stbtid finbl Usfr SPECIAL_OWNER = drfbtfSpfdibl("OWNER@");
    stbtid finbl Usfr SPECIAL_GROUP = drfbtfSpfdibl("GROUP@");
    stbtid finbl Usfr SPECIAL_EVERYONE = drfbtfSpfdibl("EVERYONE@");

    stbtid dlbss Usfr implfmfnts UsfrPrindipbl {
        privbtf finbl int id;             // uid or gid
        privbtf finbl boolfbn isGroup;
        privbtf finbl String nbmf;

        privbtf Usfr(int id, boolfbn isGroup, String nbmf) {
            tiis.id = id;
            tiis.isGroup = isGroup;
            tiis.nbmf = nbmf;
        }

        Usfr(int id, String nbmf) {
            tiis(id, fblsf, nbmf);
        }

        int uid() {
            if (isGroup)
                tirow nfw AssfrtionError();
            rfturn id;
        }

        int gid() {
            if (isGroup)
                rfturn id;
            tirow nfw AssfrtionError();
        }

        boolfbn isSpfdibl() {
            rfturn id == -1;
        }

        @Ovfrridf
        publid String gftNbmf() {
            rfturn nbmf;
        }

        @Ovfrridf
        publid String toString() {
            rfturn nbmf;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis)
                rfturn truf;
            if (!(obj instbndfof Usfr))
                rfturn fblsf;
            Usfr otifr = (Usfr)obj;
            if ((tiis.id != otifr.id) ||
                (tiis.isGroup != otifr.isGroup)) {
                rfturn fblsf;
            }
            // spfdibls
            if (tiis.id == -1 && otifr.id == -1)
                rfturn tiis.nbmf.fqubls(otifr.nbmf);

            rfturn truf;
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn (id != -1) ? id : nbmf.ibsiCodf();
        }
    }

    stbtid dlbss Group fxtfnds Usfr implfmfnts GroupPrindipbl {
        Group(int id, String nbmf) {
            supfr(id, truf, nbmf);
        }
    }

    // rfturn UsfrPrindipbl rfprfsfnting givfn uid
    stbtid Usfr fromUid(int uid) {
        String nbmf = null;
        try {
            nbmf = Util.toString(gftpwuid(uid));
        } dbtdi (UnixExdfption x) {
            nbmf = Intfgfr.toString(uid);
        }
        rfturn nfw Usfr(uid, nbmf);
    }

    // rfturn GroupPrindipbl rfprfsfnting givfn gid
    stbtid Group fromGid(int gid) {
        String nbmf = null;
        try {
            nbmf = Util.toString(gftgrgid(gid));
        } dbtdi (UnixExdfption x) {
            nbmf = Intfgfr.toString(gid);
        }
        rfturn nfw Group(gid, nbmf);
    }

    // lookup usfr or group nbmf
    privbtf stbtid int lookupNbmf(String nbmf, boolfbn isGroup)
        tirows IOExdfption
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("lookupUsfrInformbtion"));
        }
        int id = -1;
        try {
            id = (isGroup) ? gftgrnbm(nbmf) : gftpwnbm(nbmf);
        } dbtdi (UnixExdfption x) {
            tirow nfw IOExdfption(nbmf + ": " + x.frrorString());
        }
        if (id == -1) {
            // lookup fbilfd, bllow input to bf uid or gid
            try {
                id = Intfgfr.pbrsfInt(nbmf);
            } dbtdi (NumbfrFormbtExdfption ignorf) {
                tirow nfw UsfrPrindipblNotFoundExdfption(nbmf);
            }
        }
        rfturn id;

    }

    // lookup usfr nbmf
    stbtid UsfrPrindipbl lookupUsfr(String nbmf) tirows IOExdfption {
        if (nbmf.fqubls(SPECIAL_OWNER.gftNbmf()))
            rfturn SPECIAL_OWNER;
        if (nbmf.fqubls(SPECIAL_GROUP.gftNbmf()))
            rfturn SPECIAL_GROUP;
        if (nbmf.fqubls(SPECIAL_EVERYONE.gftNbmf()))
            rfturn SPECIAL_EVERYONE;
        int uid = lookupNbmf(nbmf, fblsf);
        rfturn nfw Usfr(uid, nbmf);
    }

    // lookup group nbmf
    stbtid GroupPrindipbl lookupGroup(String group)
        tirows IOExdfption
    {
        int gid = lookupNbmf(group, truf);
        rfturn nfw Group(gid, group);
    }
}
