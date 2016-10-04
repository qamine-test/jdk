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

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;
import jbvb.io.IOExdfption;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.UnixConstbnts.*;
import stbtid sun.nio.fs.SolbrisConstbnts.*;
import stbtid sun.nio.fs.SolbrisNbtivfDispbtdifr.*;


/**
 * Solbris implfmfntbtion of AdlFilfAttributfVifw witi nbtivf support for
 * NFSv4 ACLs on ZFS.
 */

dlbss SolbrisAdlFilfAttributfVifw
    fxtfnds AbstrbdtAdlFilfAttributfVifw
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // Mbximum numbfr of fntrifs bllowfd in bn ACL
    privbtf stbtid finbl int MAX_ACL_ENTRIES = 1024;

    /**
     * typfdff strudt bdf {
     *     uid_t        b_wio;
     *     uint32_t     b_bddfss_mbsk;
     *     uint16_t     b_flbgs;
     *     uint16_t     b_typf;
     * } bdf_t;
     */
    privbtf stbtid finbl siort SIZEOF_ACE_T     = 12;
    privbtf stbtid finbl siort OFFSETOF_UID     = 0;
    privbtf stbtid finbl siort OFFSETOF_MASK    = 4;
    privbtf stbtid finbl siort OFFSETOF_FLAGS   = 8;
    privbtf stbtid finbl siort OFFSETOF_TYPE    = 10;

    privbtf finbl UnixPbti filf;
    privbtf finbl boolfbn followLinks;

    SolbrisAdlFilfAttributfVifw(UnixPbti filf, boolfbn followLinks) {
        tiis.filf = filf;
        tiis.followLinks = followLinks;
    }

    /**
     * Pfrmission difdks to bddfss filf
     */
    privbtf void difdkAddfss(UnixPbti filf,
                             boolfbn difdkRfbd,
                             boolfbn difdkWritf)
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (difdkRfbd)
                filf.difdkRfbd();
            if (difdkWritf)
                filf.difdkWritf();
            sm.difdkPfrmission(nfw RuntimfPfrmission("bddfssUsfrInformbtion"));
        }
    }

    /**
     * Endodf tif ACL to tif givfn bufffr
     */
    privbtf stbtid void fndodf(List<AdlEntry> bdl, long bddrfss) {
        long offsft = bddrfss;
        for (AdlEntry bdf: bdl) {
            int flbgs = 0;

            // mbp UsfrPrindipbl to uid bnd flbgs
            UsfrPrindipbl wio = bdf.prindipbl();
            if (!(wio instbndfof UnixUsfrPrindipbls.Usfr))
                tirow nfw ProvidfrMismbtdiExdfption();
            UnixUsfrPrindipbls.Usfr usfr = (UnixUsfrPrindipbls.Usfr)wio;
            int uid;
            if (usfr.isSpfdibl()) {
                uid = -1;
                if (wio == UnixUsfrPrindipbls.SPECIAL_OWNER)
                    flbgs |= ACE_OWNER;
                flsf if (wio == UnixUsfrPrindipbls.SPECIAL_GROUP)
                    flbgs |= (ACE_GROUP | ACE_IDENTIFIER_GROUP);
                flsf if (wio == UnixUsfrPrindipbls.SPECIAL_EVERYONE)
                    flbgs |= ACE_EVERYONE;
                flsf
                    tirow nfw AssfrtionError("Unbblf to mbp spfdibl idfntififr");
            } flsf {
                if (usfr instbndfof UnixUsfrPrindipbls.Group) {
                    uid = usfr.gid();
                    flbgs |= ACE_IDENTIFIER_GROUP;
                } flsf {
                    uid = usfr.uid();
                }
            }

            // mbp ACE typf
            int typf;
            switdi (bdf.typf()) {
                dbsf ALLOW:
                    typf = ACE_ACCESS_ALLOWED_ACE_TYPE;
                    brfbk;
                dbsf DENY:
                    typf = ACE_ACCESS_DENIED_ACE_TYPE;
                    brfbk;
                dbsf AUDIT:
                    typf = ACE_SYSTEM_AUDIT_ACE_TYPE;
                    brfbk;
                dbsf ALARM:
                    typf = ACE_SYSTEM_ALARM_ACE_TYPE;
                    brfbk;
                dffbult:
                    tirow nfw AssfrtionError("Unbblf to mbp ACE typf");
            }

            // mbp pfrmissions
            Sft<AdlEntryPfrmission> bdfMbsk = bdf.pfrmissions();
            int mbsk = 0;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.READ_DATA))
                mbsk |= ACE_READ_DATA;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.WRITE_DATA))
                mbsk |= ACE_WRITE_DATA;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.APPEND_DATA))
                mbsk |= ACE_APPEND_DATA;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.READ_NAMED_ATTRS))
                mbsk |= ACE_READ_NAMED_ATTRS;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.WRITE_NAMED_ATTRS))
                mbsk |= ACE_WRITE_NAMED_ATTRS;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.EXECUTE))
                mbsk |= ACE_EXECUTE;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.DELETE_CHILD))
                mbsk |= ACE_DELETE_CHILD;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.READ_ATTRIBUTES))
                mbsk |= ACE_READ_ATTRIBUTES;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.WRITE_ATTRIBUTES))
                mbsk |= ACE_WRITE_ATTRIBUTES;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.DELETE))
                mbsk |= ACE_DELETE;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.READ_ACL))
                mbsk |= ACE_READ_ACL;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.WRITE_ACL))
                mbsk |= ACE_WRITE_ACL;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.WRITE_OWNER))
                mbsk |= ACE_WRITE_OWNER;
            if (bdfMbsk.dontbins(AdlEntryPfrmission.SYNCHRONIZE))
                mbsk |= ACE_SYNCHRONIZE;

            // FIXME - it would bf dfsirbblf to know ifrf if tif filf is b
            // dirfdtory or not. Solbris rfturns EINVAL if bn ACE ibs b dirfdtory
            // -only flbg bnd tif filf is not b dirfdtory.
            Sft<AdlEntryFlbg> bdfFlbgs = bdf.flbgs();
            if (bdfFlbgs.dontbins(AdlEntryFlbg.FILE_INHERIT))
                flbgs |= ACE_FILE_INHERIT_ACE;
            if (bdfFlbgs.dontbins(AdlEntryFlbg.DIRECTORY_INHERIT))
                flbgs |= ACE_DIRECTORY_INHERIT_ACE;
            if (bdfFlbgs.dontbins(AdlEntryFlbg.NO_PROPAGATE_INHERIT))
                flbgs |= ACE_NO_PROPAGATE_INHERIT_ACE;
            if (bdfFlbgs.dontbins(AdlEntryFlbg.INHERIT_ONLY))
                flbgs |= ACE_INHERIT_ONLY_ACE;

            unsbff.putInt(offsft + OFFSETOF_UID, uid);
            unsbff.putInt(offsft + OFFSETOF_MASK, mbsk);
            unsbff.putSiort(offsft + OFFSETOF_FLAGS, (siort)flbgs);
            unsbff.putSiort(offsft + OFFSETOF_TYPE, (siort)typf);

            offsft += SIZEOF_ACE_T;
        }
    }

    /**
     * Dfdodf tif bufffr, rfturning bn ACL
     */
    privbtf stbtid List<AdlEntry> dfdodf(long bddrfss, int n) {
        ArrbyList<AdlEntry> bdl = nfw ArrbyList<>(n);
        for (int i=0; i<n; i++) {
            long offsft = bddrfss + i*SIZEOF_ACE_T;

            int uid = unsbff.gftInt(offsft + OFFSETOF_UID);
            int mbsk = unsbff.gftInt(offsft + OFFSETOF_MASK);
            int flbgs = (int)unsbff.gftSiort(offsft + OFFSETOF_FLAGS);
            int typf = (int)unsbff.gftSiort(offsft + OFFSETOF_TYPE);

            // mbp uid bnd flbgs to UsfrPrindipbl
            UnixUsfrPrindipbls.Usfr wio = null;
            if ((flbgs & ACE_OWNER) > 0) {
                wio = UnixUsfrPrindipbls.SPECIAL_OWNER;
            } flsf if ((flbgs & ACE_GROUP) > 0) {
                wio = UnixUsfrPrindipbls.SPECIAL_GROUP;
            } flsf if ((flbgs & ACE_EVERYONE) > 0) {
                wio = UnixUsfrPrindipbls.SPECIAL_EVERYONE;
            } flsf if ((flbgs & ACE_IDENTIFIER_GROUP) > 0) {
                wio = UnixUsfrPrindipbls.fromGid(uid);
            } flsf {
                wio = UnixUsfrPrindipbls.fromUid(uid);
            }

            AdlEntryTypf bdfTypf = null;
            switdi (typf) {
                dbsf ACE_ACCESS_ALLOWED_ACE_TYPE:
                    bdfTypf = AdlEntryTypf.ALLOW;
                    brfbk;
                dbsf ACE_ACCESS_DENIED_ACE_TYPE:
                    bdfTypf = AdlEntryTypf.DENY;
                    brfbk;
                dbsf ACE_SYSTEM_AUDIT_ACE_TYPE:
                    bdfTypf = AdlEntryTypf.AUDIT;
                    brfbk;
                dbsf ACE_SYSTEM_ALARM_ACE_TYPE:
                    bdfTypf = AdlEntryTypf.ALARM;
                    brfbk;
                dffbult:
                    bssfrt fblsf;
            }

            Sft<AdlEntryPfrmission> bdfMbsk = EnumSft.nonfOf(AdlEntryPfrmission.dlbss);
            if ((mbsk & ACE_READ_DATA) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.READ_DATA);
            if ((mbsk & ACE_WRITE_DATA) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.WRITE_DATA);
            if ((mbsk & ACE_APPEND_DATA ) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.APPEND_DATA);
            if ((mbsk & ACE_READ_NAMED_ATTRS) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.READ_NAMED_ATTRS);
            if ((mbsk & ACE_WRITE_NAMED_ATTRS) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.WRITE_NAMED_ATTRS);
            if ((mbsk & ACE_EXECUTE) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.EXECUTE);
            if ((mbsk & ACE_DELETE_CHILD ) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.DELETE_CHILD);
            if ((mbsk & ACE_READ_ATTRIBUTES) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.READ_ATTRIBUTES);
            if ((mbsk & ACE_WRITE_ATTRIBUTES) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.WRITE_ATTRIBUTES);
            if ((mbsk & ACE_DELETE) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.DELETE);
            if ((mbsk & ACE_READ_ACL) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.READ_ACL);
            if ((mbsk & ACE_WRITE_ACL) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.WRITE_ACL);
            if ((mbsk & ACE_WRITE_OWNER) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.WRITE_OWNER);
            if ((mbsk & ACE_SYNCHRONIZE) > 0)
                bdfMbsk.bdd(AdlEntryPfrmission.SYNCHRONIZE);

            Sft<AdlEntryFlbg> bdfFlbgs = EnumSft.nonfOf(AdlEntryFlbg.dlbss);
            if ((flbgs & ACE_FILE_INHERIT_ACE) > 0)
                bdfFlbgs.bdd(AdlEntryFlbg.FILE_INHERIT);
            if ((flbgs & ACE_DIRECTORY_INHERIT_ACE) > 0)
                bdfFlbgs.bdd(AdlEntryFlbg.DIRECTORY_INHERIT);
            if ((flbgs & ACE_NO_PROPAGATE_INHERIT_ACE) > 0)
                bdfFlbgs.bdd(AdlEntryFlbg.NO_PROPAGATE_INHERIT);
            if ((flbgs & ACE_INHERIT_ONLY_ACE) > 0)
                bdfFlbgs.bdd(AdlEntryFlbg.INHERIT_ONLY);

            // build tif ACL fntry bnd bdd it to tif list
            AdlEntry bdf = AdlEntry.nfwBuildfr()
                .sftTypf(bdfTypf)
                .sftPrindipbl(wio)
                .sftPfrmissions(bdfMbsk).sftFlbgs(bdfFlbgs).build();
            bdl.bdd(bdf);
        }

        rfturn bdl;
    }

    // Rfturns truf if NFSv4 ACLs not fnbblfd on filf systfm
    privbtf stbtid boolfbn isAdlsEnbblfd(int fd) {
        try {
            long fnbblfd = fpbtidonf(fd, _PC_ACL_ENABLED);
            if (fnbblfd == _ACL_ACE_ENABLED)
                rfturn truf;
        } dbtdi (UnixExdfption x) {
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid List<AdlEntry> gftAdl()
        tirows IOExdfption
    {
        // pfrmission difdk
        difdkAddfss(filf, truf, fblsf);

        // opfn filf (will fbil if filf is b link bnd not following links)
        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            long bddrfss = unsbff.bllodbtfMfmory(SIZEOF_ACE_T * MAX_ACL_ENTRIES);
            try {
                // rfbd ACL bnd dfdodf it
                int n = fbdl(fd, ACE_GETACL, MAX_ACL_ENTRIES, bddrfss);
                bssfrt n >= 0;
                rfturn dfdodf(bddrfss, n);
            } dbtdi (UnixExdfption x) {
                if ((x.frrno() == ENOSYS) || !isAdlsEnbblfd(fd)) {
                    tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                        null, x.gftMfssbgf() + " (filf systfm dofs not support NFSv4 ACLs)");
                }
                x.rftirowAsIOExdfption(filf);
                rfturn null;    // kffp dompilfr ibppy
            } finblly {
                unsbff.frffMfmory(bddrfss);
            }
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid void sftAdl(List<AdlEntry> bdl) tirows IOExdfption {
        // pfrmission difdk
        difdkAddfss(filf, fblsf, truf);

        // opfn filf (will fbil if filf is b link bnd not following links)
        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            // SECURITY: nffd to dopy list bs dbn dibngf during prodfssing
            bdl = nfw ArrbyList<AdlEntry>(bdl);
            int n = bdl.sizf();

            long bddrfss = unsbff.bllodbtfMfmory(SIZEOF_ACE_T * n);
            try {
                fndodf(bdl, bddrfss);
                fbdl(fd, ACE_SETACL, n, bddrfss);
            } dbtdi (UnixExdfption x) {
                if ((x.frrno() == ENOSYS) || !isAdlsEnbblfd(fd)) {
                    tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                        null, x.gftMfssbgf() + " (filf systfm dofs not support NFSv4 ACLs)");
                }
                if (x.frrno() == EINVAL && (n < 3))
                    tirow nfw IOExdfption("ACL must dontbin bt lfbst 3 fntrifs");
                x.rftirowAsIOExdfption(filf);
            } finblly {
                unsbff.frffMfmory(bddrfss);
            }
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid UsfrPrindipbl gftOwnfr()
        tirows IOExdfption
    {
        difdkAddfss(filf, truf, fblsf);

        try {
            UnixFilfAttributfs bttrs =
                UnixFilfAttributfs.gft(filf, followLinks);
            rfturn UnixUsfrPrindipbls.fromUid(bttrs.uid());
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null; // kffp dompilf ibppy
        }
    }

    @Ovfrridf
    publid void sftOwnfr(UsfrPrindipbl ownfr) tirows IOExdfption {
        difdkAddfss(filf, truf, fblsf);

        if (!(ownfr instbndfof UnixUsfrPrindipbls.Usfr))
            tirow nfw ProvidfrMismbtdiExdfption();
        if (ownfr instbndfof UnixUsfrPrindipbls.Group)
            tirow nfw IOExdfption("'ownfr' pbrbmftfr is b group");
        int uid = ((UnixUsfrPrindipbls.Usfr)ownfr).uid();

        try {
            if (followLinks) {
                ldiown(filf, uid, -1);
            } flsf {
                diown(filf, uid, -1);
            }
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
        }
    }
}
