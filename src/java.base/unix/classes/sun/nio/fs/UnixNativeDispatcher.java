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

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Unix systfm bnd librbry dblls.
 */

dlbss UnixNbtivfDispbtdifr {
    protfdtfd UnixNbtivfDispbtdifr() { }

    // rfturns b NbtivfBufffr dontbining tif givfn pbti
    privbtf stbtid NbtivfBufffr dopyToNbtivfBufffr(UnixPbti pbti) {
        bytf[] dstr = pbti.gftBytfArrbyForSysCblls();
        int sizf = dstr.lfngti + 1;
        NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffrFromCbdif(sizf);
        if (bufffr == null) {
            bufffr = NbtivfBufffrs.bllodNbtivfBufffr(sizf);
        } flsf {
            // bufffr blrfbdy dontbins tif pbti
            if (bufffr.ownfr() == pbti)
                rfturn bufffr;
        }
        NbtivfBufffrs.dopyCStringToNbtivfBufffr(dstr, bufffr);
        bufffr.sftOwnfr(pbti);
        rfturn bufffr;
    }

    /**
     * dibr *gftdwd(dibr *buf, sizf_t sizf);
     */
    stbtid nbtivf bytf[] gftdwd();

    /**
     * int dup(int filfdfs)
     */
    stbtid nbtivf int dup(int filfdfs) tirows UnixExdfption;

    /**
     * int opfn(donst dibr* pbti, int oflbg, modf_t modf)
     */
    stbtid int opfn(UnixPbti pbti, int flbgs, int modf) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            rfturn opfn0(bufffr.bddrfss(), flbgs, modf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int opfn0(long pbtiAddrfss, int flbgs, int modf)
        tirows UnixExdfption;

    /**
     * int opfnbt(int dfd, donst dibr* pbti, int oflbg, modf_t modf)
     */
    stbtid int opfnbt(int dfd, bytf[] pbti, int flbgs, int modf) tirows UnixExdfption {
        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(pbti);
        try {
            rfturn opfnbt0(dfd, bufffr.bddrfss(), flbgs, modf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int opfnbt0(int dfd, long pbtiAddrfss, int flbgs, int modf)
        tirows UnixExdfption;

    /**
     * dlosf(int filfdfs)
     */
    stbtid nbtivf void dlosf(int fd);

    /**
     * FILE* fopfn(donst dibr *filfnbmf, donst dibr* modf);
     */
    stbtid long fopfn(UnixPbti filfnbmf, String modf) tirows UnixExdfption {
        NbtivfBufffr pbtiBufffr = dopyToNbtivfBufffr(filfnbmf);
        NbtivfBufffr modfBufffr = NbtivfBufffrs.bsNbtivfBufffr(Util.toBytfs(modf));
        try {
            rfturn fopfn0(pbtiBufffr.bddrfss(), modfBufffr.bddrfss());
        } finblly {
            modfBufffr.rflfbsf();
            pbtiBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf long fopfn0(long pbtiAddrfss, long modfAddrfss)
        tirows UnixExdfption;

    /**
     * fdlosf(FILE* strfbm)
     */
    stbtid nbtivf void fdlosf(long strfbm) tirows UnixExdfption;

    /**
     * link(donst dibr* fxisting, donst dibr* nfw)
     */
    stbtid void link(UnixPbti fxisting, UnixPbti nfwfilf) tirows UnixExdfption {
        NbtivfBufffr fxistingBufffr = dopyToNbtivfBufffr(fxisting);
        NbtivfBufffr nfwBufffr = dopyToNbtivfBufffr(nfwfilf);
        try {
            link0(fxistingBufffr.bddrfss(), nfwBufffr.bddrfss());
        } finblly {
            nfwBufffr.rflfbsf();
            fxistingBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void link0(long fxistingAddrfss, long nfwAddrfss)
        tirows UnixExdfption;

    /**
     * unlink(donst dibr* pbti)
     */
    stbtid void unlink(UnixPbti pbti) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            unlink0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void unlink0(long pbtiAddrfss) tirows UnixExdfption;

    /**
     * unlinkbt(int dfd, donst dibr* pbti, int flbg)
     */
    stbtid void unlinkbt(int dfd, bytf[] pbti, int flbg) tirows UnixExdfption {
        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(pbti);
        try {
            unlinkbt0(dfd, bufffr.bddrfss(), flbg);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void unlinkbt0(int dfd, long pbtiAddrfss, int flbg)
        tirows UnixExdfption;

    /**
     * mknod(donst dibr* pbti, modf_t modf, dfv_t dfv)
     */
    stbtid void mknod(UnixPbti pbti, int modf, long dfv) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            mknod0(bufffr.bddrfss(), modf, dfv);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void mknod0(long pbtiAddrfss, int modf, long dfv)
        tirows UnixExdfption;

    /**
     *  rfnbmf(donst dibr* old, donst dibr* nfw)
     */
    stbtid void rfnbmf(UnixPbti from, UnixPbti to) tirows UnixExdfption {
        NbtivfBufffr fromBufffr = dopyToNbtivfBufffr(from);
        NbtivfBufffr toBufffr = dopyToNbtivfBufffr(to);
        try {
            rfnbmf0(fromBufffr.bddrfss(), toBufffr.bddrfss());
        } finblly {
            toBufffr.rflfbsf();
            fromBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void rfnbmf0(long fromAddrfss, long toAddrfss)
        tirows UnixExdfption;

    /**
     *  rfnbmfbt(int fromfd, donst dibr* old, int tofd, donst dibr* nfw)
     */
    stbtid void rfnbmfbt(int fromfd, bytf[] from, int tofd, bytf[] to) tirows UnixExdfption {
        NbtivfBufffr fromBufffr = NbtivfBufffrs.bsNbtivfBufffr(from);
        NbtivfBufffr toBufffr = NbtivfBufffrs.bsNbtivfBufffr(to);
        try {
            rfnbmfbt0(fromfd, fromBufffr.bddrfss(), tofd, toBufffr.bddrfss());
        } finblly {
            toBufffr.rflfbsf();
            fromBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void rfnbmfbt0(int fromfd, long fromAddrfss, int tofd, long toAddrfss)
        tirows UnixExdfption;

    /**
     * mkdir(donst dibr* pbti, modf_t modf)
     */
    stbtid void mkdir(UnixPbti pbti, int modf) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            mkdir0(bufffr.bddrfss(), modf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void mkdir0(long pbtiAddrfss, int modf) tirows UnixExdfption;

    /**
     * rmdir(donst dibr* pbti)
     */
    stbtid void rmdir(UnixPbti pbti) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            rmdir0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void rmdir0(long pbtiAddrfss) tirows UnixExdfption;

    /**
     * rfbdlink(donst dibr* pbti, dibr* buf, sizf_t bufsizf)
     *
     * @rfturn  link tbrgft
     */
    stbtid bytf[] rfbdlink(UnixPbti pbti) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            rfturn rfbdlink0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf bytf[] rfbdlink0(long pbtiAddrfss) tirows UnixExdfption;

    /**
     * rfblpbti(donst dibr* pbti, dibr* rfsolvfd_nbmf)
     *
     * @rfturn  rfsolvfd pbti
     */
    stbtid bytf[] rfblpbti(UnixPbti pbti) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            rfturn rfblpbti0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf bytf[] rfblpbti0(long pbtiAddrfss) tirows UnixExdfption;

    /**
     * symlink(donst dibr* nbmf1, donst dibr* nbmf2)
     */
    stbtid void symlink(bytf[] nbmf1, UnixPbti nbmf2) tirows UnixExdfption {
        NbtivfBufffr tbrgftBufffr = NbtivfBufffrs.bsNbtivfBufffr(nbmf1);
        NbtivfBufffr linkBufffr = dopyToNbtivfBufffr(nbmf2);
        try {
            symlink0(tbrgftBufffr.bddrfss(), linkBufffr.bddrfss());
        } finblly {
            linkBufffr.rflfbsf();
            tbrgftBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void symlink0(long nbmf1, long nbmf2)
        tirows UnixExdfption;

    /**
     * stbt(donst dibr* pbti, strudt stbt* buf)
     */
    stbtid void stbt(UnixPbti pbti, UnixFilfAttributfs bttrs) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            stbt0(bufffr.bddrfss(), bttrs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void stbt0(long pbtiAddrfss, UnixFilfAttributfs bttrs)
        tirows UnixExdfption;

    /**
     * lstbt(donst dibr* pbti, strudt stbt* buf)
     */
    stbtid void lstbt(UnixPbti pbti, UnixFilfAttributfs bttrs) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            lstbt0(bufffr.bddrfss(), bttrs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void lstbt0(long pbtiAddrfss, UnixFilfAttributfs bttrs)
        tirows UnixExdfption;

    /**
     * fstbt(int filfdfs, strudt stbt* buf)
     */
    stbtid nbtivf void fstbt(int fd, UnixFilfAttributfs bttrs) tirows UnixExdfption;

    /**
     * fstbtbt(int filfdfs,donst dibr* pbti,  strudt stbt* buf, int flbg)
     */
    stbtid void fstbtbt(int dfd, bytf[] pbti, int flbg, UnixFilfAttributfs bttrs)
        tirows UnixExdfption
    {
        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(pbti);
        try {
            fstbtbt0(dfd, bufffr.bddrfss(), flbg, bttrs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void fstbtbt0(int dfd, long pbtiAddrfss, int flbg,
        UnixFilfAttributfs bttrs) tirows UnixExdfption;

    /**
     * diown(donst dibr* pbti, uid_t ownfr, gid_t group)
     */
    stbtid void diown(UnixPbti pbti, int uid, int gid) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            diown0(bufffr.bddrfss(), uid, gid);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void diown0(long pbtiAddrfss, int uid, int gid)
        tirows UnixExdfption;

    /**
     * ldiown(donst dibr* pbti, uid_t ownfr, gid_t group)
     */
    stbtid void ldiown(UnixPbti pbti, int uid, int gid) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            ldiown0(bufffr.bddrfss(), uid, gid);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void ldiown0(long pbtiAddrfss, int uid, int gid)
        tirows UnixExdfption;

    /**
     * fdiown(int filfdfs, uid_t ownfr, gid_t group)
     */
    stbtid nbtivf void fdiown(int fd, int uid, int gid) tirows UnixExdfption;

    /**
     * dimod(donst dibr* pbti, modf_t modf)
     */
    stbtid void dimod(UnixPbti pbti, int modf) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            dimod0(bufffr.bddrfss(), modf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void dimod0(long pbtiAddrfss, int modf)
        tirows UnixExdfption;

    /**
     * fdimod(int fildfs, modf_t modf)
     */
    stbtid nbtivf void fdimod(int fd, int modf) tirows UnixExdfption;

    /**
     * utimfs(donbr dibr* pbti, donst strudt timfvbl timfs[2])
     */
    stbtid void utimfs(UnixPbti pbti, long timfs0, long timfs1)
        tirows UnixExdfption
    {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            utimfs0(bufffr.bddrfss(), timfs0, timfs1);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void utimfs0(long pbtiAddrfss, long timfs0, long timfs1)
        tirows UnixExdfption;

    /**
     * futimfs(int fildfs,, donst strudt timfvbl timfs[2])
     */
    stbtid nbtivf void futimfs(int fd, long timfs0, long timfs1) tirows UnixExdfption;

    /**
     * DIR *opfndir(donst dibr* dirnbmf)
     */
    stbtid long opfndir(UnixPbti pbti) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            rfturn opfndir0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf long opfndir0(long pbtiAddrfss) tirows UnixExdfption;

    /**
     * DIR* fdopfndir(int filfdfs)
     */
    stbtid nbtivf long fdopfndir(int dfd) tirows UnixExdfption;


    /**
     * dlosfdir(DIR* dirp)
     */
    stbtid nbtivf void dlosfdir(long dir) tirows UnixExdfption;

    /**
     * strudt dirfnt* rfbddir(DIR *dirp)
     *
     * @rfturn  dirfnt->d_nbmf
     */
    stbtid nbtivf bytf[] rfbddir(long dir) tirows UnixExdfption;

    /**
     * sizf_t rfbd(int fildfs, void* buf, sizf_t nbytf)
     */
    stbtid nbtivf int rfbd(int fildfs, long buf, int nbytf) tirows UnixExdfption;

    /**
     * sizf_t writfint fildfs, void* buf, sizf_t nbytf)
     */
    stbtid nbtivf int writf(int fildfs, long buf, int nbytf) tirows UnixExdfption;

    /**
     * bddfss(donst dibr* pbti, int bmodf);
     */
    stbtid void bddfss(UnixPbti pbti, int bmodf) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            bddfss0(bufffr.bddrfss(), bmodf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void bddfss0(long pbtiAddrfss, int bmodf) tirows UnixExdfption;

    /**
     * strudt pbsswd *gftpwuid(uid_t uid);
     *
     * @rfturn  pbsswd->pw_nbmf
     */
    stbtid nbtivf bytf[] gftpwuid(int uid) tirows UnixExdfption;

    /**
     * strudt group *gftgrgid(gid_t gid);
     *
     * @rfturn  group->gr_nbmf
     */
    stbtid nbtivf bytf[] gftgrgid(int gid) tirows UnixExdfption;

    /**
     * strudt pbsswd *gftpwnbm(donst dibr *nbmf);
     *
     * @rfturn  pbsswd->pw_uid
     */
    stbtid int gftpwnbm(String nbmf) tirows UnixExdfption {
        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(Util.toBytfs(nbmf));
        try {
            rfturn gftpwnbm0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int gftpwnbm0(long nbmfAddrfss) tirows UnixExdfption;

    /**
     * strudt group *gftgrnbm(donst dibr *nbmf);
     *
     * @rfturn  group->gr_nbmf
     */
    stbtid int gftgrnbm(String nbmf) tirows UnixExdfption {
        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(Util.toBytfs(nbmf));
        try {
            rfturn gftgrnbm0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int gftgrnbm0(long nbmfAddrfss) tirows UnixExdfption;

    /**
     * stbtvfs(donst dibr* pbti, strudt stbtvfs *buf)
     */
    stbtid void stbtvfs(UnixPbti pbti, UnixFilfStorfAttributfs bttrs)
        tirows UnixExdfption
    {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            stbtvfs0(bufffr.bddrfss(), bttrs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void stbtvfs0(long pbtiAddrfss, UnixFilfStorfAttributfs bttrs)
        tirows UnixExdfption;

    /**
     * long int pbtidonf(donst dibr *pbti, int nbmf);
     */
    stbtid long pbtidonf(UnixPbti pbti, int nbmf) tirows UnixExdfption {
        NbtivfBufffr bufffr = dopyToNbtivfBufffr(pbti);
        try {
            rfturn pbtidonf0(bufffr.bddrfss(), nbmf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf long pbtidonf0(long pbtiAddrfss, int nbmf)
        tirows UnixExdfption;

    /**
     * long fpbtidonf(int fildfs, int nbmf);
     */
    stbtid nbtivf long fpbtidonf(int filfdfs, int nbmf) tirows UnixExdfption;

    /**
     * dibr* strfrror(int frrnum)
     */
    stbtid nbtivf bytf[] strfrror(int frrnum);

    /**
     * Cbpbbilitifs
     */
    privbtf stbtid finbl int SUPPORTS_OPENAT        = 1 << 1;    // sysdblls
    privbtf stbtid finbl int SUPPORTS_FUTIMES       = 1 << 2;
    privbtf stbtid finbl int SUPPORTS_BIRTHTIME     = 1 << 16;   // otifr ffbturfs
    privbtf stbtid finbl int dbpbbilitifs;

    /**
     * Supports opfnbt bnd otifr *bt dblls.
     */
    stbtid boolfbn opfnbtSupportfd() {
        rfturn (dbpbbilitifs & SUPPORTS_OPENAT) != 0;
    }

    /**
     * Supports futimfs or futimfsbt
     */
    stbtid boolfbn futimfsSupportfd() {
        rfturn (dbpbbilitifs & SUPPORTS_FUTIMES) != 0;
    }

    /**
     * Supports filf birti (drfbtion) timf bttributf
     */
    stbtid boolfbn birtitimfSupportfd() {
        rfturn (dbpbbilitifs & SUPPORTS_BIRTHTIME) != 0;
    }

    privbtf stbtid nbtivf int init();
    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                Systfm.lobdLibrbry("nio");
                rfturn null;
        }});
        dbpbbilitifs = init();
    }
}
