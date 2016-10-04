/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.io.*;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;


dlbss SourdfCibnnflImpl
    fxtfnds Pipf.SourdfCibnnfl
    implfmfnts SflCiImpl
{

    // Usfd to mbkf nbtivf rfbd bnd writf dblls
    privbtf stbtid finbl NbtivfDispbtdifr nd = nfw FilfDispbtdifrImpl();

    // Tif filf dfsdriptor bssodibtfd witi tiis dibnnfl
    FilfDfsdriptor fd;

    // fd vbluf nffdfd for dfv/poll. Tiis vbluf will rfmbin vblid
    // fvfn bftfr tif vbluf in tif filf dfsdriptor objfdt ibs bffn sft to -1
    int fdVbl;

    // ID of nbtivf tirfbd doing rfbd, for signblling
    privbtf volbtilf long tirfbd = 0;

    // Lodk ifld by durrfnt rfbding tirfbd
    privbtf finbl Objfdt lodk = nfw Objfdt();

    // Lodk ifld by bny tirfbd tibt modififs tif stbtf fiflds dfdlbrfd bflow
    // DO NOT invokf b blodking I/O opfrbtion wiilf iolding tiis lodk!
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    // -- Tif following fiflds brf protfdtfd by stbtfLodk

    // Cibnnfl stbtf
    privbtf stbtid finbl int ST_UNINITIALIZED = -1;
    privbtf stbtid finbl int ST_INUSE = 0;
    privbtf stbtid finbl int ST_KILLED = 1;
    privbtf volbtilf int stbtf = ST_UNINITIALIZED;

    // -- End of fiflds protfdtfd by stbtfLodk


    publid FilfDfsdriptor gftFD() {
        rfturn fd;
    }

    publid int gftFDVbl() {
        rfturn fdVbl;
    }

    SourdfCibnnflImpl(SflfdtorProvidfr sp, FilfDfsdriptor fd) {
        supfr(sp);
        tiis.fd = fd;
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = ST_INUSE;
    }

    protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (stbtf != ST_KILLED)
                nd.prfClosf(fd);
            long ti = tirfbd;
            if (ti != 0)
                NbtivfTirfbd.signbl(ti);
            if (!isRfgistfrfd())
                kill();
        }
    }

    publid void kill() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (stbtf == ST_KILLED)
                rfturn;
            if (stbtf == ST_UNINITIALIZED) {
                stbtf = ST_KILLED;
                rfturn;
            }
            bssfrt !isOpfn() && !isRfgistfrfd();
            nd.dlosf(fd);
            stbtf = ST_KILLED;
        }
    }

    protfdtfd void implConfigurfBlodking(boolfbn blodk) tirows IOExdfption {
        IOUtil.donfigurfBlodking(fd, blodk);
    }

    publid boolfbn trbnslbtfRfbdyOps(int ops, int initiblOps,
                                     SflfdtionKfyImpl sk) {
        int intOps = sk.nioIntfrfstOps(); // Do tiis just ondf, it syndironizfs
        int oldOps = sk.nioRfbdyOps();
        int nfwOps = initiblOps;

        if ((ops & Nft.POLLNVAL) != 0)
            tirow nfw Error("POLLNVAL dftfdtfd");

        if ((ops & (Nft.POLLERR | Nft.POLLHUP)) != 0) {
            nfwOps = intOps;
            sk.nioRfbdyOps(nfwOps);
            rfturn (nfwOps & ~oldOps) != 0;
        }

        if (((ops & Nft.POLLIN) != 0) &&
            ((intOps & SflfdtionKfy.OP_READ) != 0))
            nfwOps |= SflfdtionKfy.OP_READ;

        sk.nioRfbdyOps(nfwOps);
        rfturn (nfwOps & ~oldOps) != 0;
    }

    publid boolfbn trbnslbtfAndUpdbtfRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, sk.nioRfbdyOps(), sk);
    }

    publid boolfbn trbnslbtfAndSftRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, 0, sk);
    }

    publid void trbnslbtfAndSftIntfrfstOps(int ops, SflfdtionKfyImpl sk) {
        if (ops == SflfdtionKfy.OP_READ)
            ops = Nft.POLLIN;
        sk.sflfdtor.putEvfntOps(sk, ops);
    }

    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (!isOpfn())
            tirow nfw ClosfdCibnnflExdfption();
    }

    publid int rfbd(BytfBufffr dst) tirows IOExdfption {
        fnsurfOpfn();
        syndironizfd (lodk) {
            int n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                tirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = IOUtil.rfbd(fd, dst, -1, nd);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn IOStbtus.normblizf(n);
            } finblly {
                tirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    publid long rfbd(BytfBufffr[] dsts, int offsft, int lfngti)
        tirows IOExdfption
    {
        if ((offsft < 0) || (lfngti < 0) || (offsft > dsts.lfngti - lfngti))
           tirow nfw IndfxOutOfBoundsExdfption();
        rfturn rfbd(Util.subsfqufndf(dsts, offsft, lfngti));
    }

    publid long rfbd(BytfBufffr[] dsts) tirows IOExdfption {
        if (dsts == null)
            tirow nfw NullPointfrExdfption();
        fnsurfOpfn();
        syndironizfd (lodk) {
            long n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                tirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = IOUtil.rfbd(fd, dsts, nd);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn IOStbtus.normblizf(n);
            } finblly {
                tirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }
}
