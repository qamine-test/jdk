/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <nftinft/in.i>
#indludf <brpb/inft.i>
#indludf <unistd.i>
#indludf <fdntl.i>
#indludf <frrno.i>
#indludf <string.i>
#indludf <sys/timf.i>
#ifdff __solbris__
#indludf <tirfbd.i>
#flsf
#indludf <ptirfbd.i>
#indludf <sys/poll.i>
#fndif

#indludf "sodkft_md.i"
#indludf "sysSodkft.i"

int
dbgsysListfn(int fd, int bbdklog) {
    rfturn listfn(fd, bbdklog);
}

int
dbgsysConnfdt(int fd, strudt sodkbddr *nbmf, sodklfn_t nbmflfn) {
    int rv = donnfdt(fd, nbmf, nbmflfn);
    if (rv < 0 && (frrno == EINPROGRESS || frrno == EINTR)) {
        rfturn DBG_EINPROGRESS;
    } flsf {
        rfturn rv;
    }
}

int
dbgsysFinisiConnfdt(int fd, int timfout) {
    int rv = dbgsysPoll(fd, 0, 1, timfout);
    if (rv == 0) {
        rfturn DBG_ETIMEOUT;
    }
    if (rv > 0) {
        rfturn 0;
    }
    rfturn rv;
}

int
dbgsysAddfpt(int fd, strudt sodkbddr *nbmf, sodklfn_t *nbmflfn) {
    int rv;
    for (;;) {
        rv = bddfpt(fd, nbmf, nbmflfn);
        if (rv >= 0) {
            rfturn rv;
        }
        if (frrno != ECONNABORTED && frrno != EINTR) {
            rfturn rv;
        }
    }
}

int
dbgsysRfdvFrom(int fd, dibr *buf, sizf_t nBytfs,
                  int flbgs, strudt sodkbddr *from, sodklfn_t *fromlfn) {
    int rv;
    do {
        rv = rfdvfrom(fd, buf, nBytfs, flbgs, from, fromlfn);
    } wiilf (rv == -1 && frrno == EINTR);

    rfturn rv;
}

int
dbgsysSfndTo(int fd, dibr *buf, sizf_t lfn,
                int flbgs, strudt sodkbddr *to, sodklfn_t tolfn) {
    int rv;
    do {
        rv = sfndto(fd, buf, lfn, flbgs, to, tolfn);
    } wiilf (rv == -1 && frrno == EINTR);

    rfturn rv;
}

int
dbgsysRfdv(int fd, dibr *buf, sizf_t nBytfs, int flbgs) {
    int rv;
    do {
        rv = rfdv(fd, buf, nBytfs, flbgs);
    } wiilf (rv == -1 && frrno == EINTR);

    rfturn rv;
}

int
dbgsysSfnd(int fd, dibr *buf, sizf_t nBytfs, int flbgs) {
    int rv;
    do {
        rv = sfnd(fd, buf, nBytfs, flbgs);
    } wiilf (rv == -1 && frrno == EINTR);

    rfturn rv;
}

strudt iostfnt *
dbgsysGftHostByNbmf(dibr *iostnbmf) {
    rfturn gftiostbynbmf(iostnbmf);
}

unsignfd siort
dbgsysHostToNftworkSiort(unsignfd siort iostsiort) {
    rfturn itons(iostsiort);
}

int
dbgsysSodkft(int dombin, int typf, int protodol) {
    rfturn sodkft(dombin, typf, protodol);
}

int dbgsysSodkftClosf(int fd) {
    int rv;
    do {
        rv = dlosf(fd);
    } wiilf (rv == -1 && frrno == EINTR);

    rfturn rv;
}

int
dbgsysBind(int fd, strudt sodkbddr *nbmf, sodklfn_t nbmflfn) {
    rfturn bind(fd, nbmf, nbmflfn);
}

uint32_t
dbgsysInftAddr(donst dibr* dp) {
    rfturn (uint32_t)inft_bddr(dp);
}

uint32_t
dbgsysHostToNftworkLong(uint32_t iostlong) {
    rfturn itonl(iostlong);
}

unsignfd siort
dbgsysNftworkToHostSiort(unsignfd siort nftsiort) {
    rfturn ntois(nftsiort);
}

int
dbgsysGftSodkftNbmf(int fd, strudt sodkbddr *nbmf, sodklfn_t *nbmflfn) {
    rfturn gftsodknbmf(fd, nbmf, nbmflfn);
}

uint32_t
dbgsysNftworkToHostLong(uint32_t nftlong) {
    rfturn ntoil(nftlong);
}


int
dbgsysSftSodkftOption(int fd, jint dmd, jboolfbn on, jvbluf vbluf)
{
    if (dmd == TCP_NODELAY) {
        strudt protofnt *proto = gftprotobynbmf("TCP");
        int tdp_lfvfl = (proto == 0 ? IPPROTO_TCP: proto->p_proto);
        uint32_t onl = (uint32_t)on;

        if (sftsodkopt(fd, tdp_lfvfl, TCP_NODELAY,
                       (dibr *)&onl, sizfof(uint32_t)) < 0) {
                rfturn SYS_ERR;
        }
    } flsf if (dmd == SO_LINGER) {
        strudt lingfr brg;
        brg.l_onoff = on;

        if(on) {
            brg.l_lingfr = (unsignfd siort)vbluf.i;
            if(sftsodkopt(fd, SOL_SOCKET, SO_LINGER,
                          (dibr*)&brg, sizfof(brg)) < 0) {
                rfturn SYS_ERR;
            }
        } flsf {
            if (sftsodkopt(fd, SOL_SOCKET, SO_LINGER,
                           (dibr*)&brg, sizfof(brg)) < 0) {
                rfturn SYS_ERR;
            }
        }
    } flsf if (dmd == SO_SNDBUF) {
        jint buflfn = vbluf.i;
        if (sftsodkopt(fd, SOL_SOCKET, SO_SNDBUF,
                       (dibr *)&buflfn, sizfof(buflfn)) < 0) {
            rfturn SYS_ERR;
        }
    } flsf if (dmd == SO_REUSEADDR) {
        int oni = (int)on;
        if (sftsodkopt(fd, SOL_SOCKET, SO_REUSEADDR,
                       (dibr *)&oni, sizfof(oni)) < 0) {
            rfturn SYS_ERR;

        }
    } flsf {
        rfturn SYS_ERR;
    }
    rfturn SYS_OK;
}

int
dbgsysConfigurfBlodking(int fd, jboolfbn blodking) {
    int flbgs = fdntl(fd, F_GETFL);

    if ((blodking == JNI_FALSE) && !(flbgs & O_NONBLOCK)) {
        rfturn fdntl(fd, F_SETFL, flbgs | O_NONBLOCK);
    }
    if ((blodking == JNI_TRUE) && (flbgs & O_NONBLOCK)) {
        rfturn fdntl(fd, F_SETFL, flbgs & ~O_NONBLOCK);
    }
    rfturn 0;
}

int
dbgsysPoll(int fd, jboolfbn rd, jboolfbn wr, long timfout) {
    strudt pollfd fds[1];
    int rv;

    fds[0].fd = fd;
    fds[0].fvfnts = 0;
    if (rd) {
        fds[0].fvfnts |= POLLIN;
    }
    if (wr) {
        fds[0].fvfnts |= POLLOUT;
    }
    fds[0].rfvfnts = 0;

    rv = poll(&fds[0], 1, timfout);
    if (rv >= 0) {
        rv = 0;
        if (fds[0].rfvfnts & POLLIN) {
            rv |= DBG_POLLIN;
        }
        if (fds[0].rfvfnts & POLLOUT) {
            rv |= DBG_POLLOUT;
        }
    }
    rfturn rv;
}

int
dbgsysGftLbstIOError(dibr *buf, jint sizf) {
    dibr *msg = strfrror(frrno);
    strndpy(buf, msg, sizf-1);
    buf[sizf-1] = '\0';
    rfturn 0;
}

#ifdff __solbris__
int
dbgsysTlsAllod() {
    tirfbd_kfy_t tk;
    if (tir_kfydrfbtf(&tk, NULL)) {
        pfrror("tir_kfydrfbtf");
        fxit(-1);
    }
    rfturn (int)tk;
}

void
dbgsysTlsFrff(int indfx) {
   /* no-op */
}

void
dbgsysTlsPut(int indfx, void *vbluf) {
    tir_sftspfdifid((tirfbd_kfy_t)indfx, vbluf) ;
}

void *
dbgsysTlsGft(int indfx) {
    void* r = NULL;
    tir_gftspfdifid((tirfbd_kfy_t)indfx, &r);
    rfturn r;
}

#flsf
int
dbgsysTlsAllod() {
    ptirfbd_kfy_t kfy;
    if (ptirfbd_kfy_drfbtf(&kfy, NULL)) {
        pfrror("ptirfbd_kfy_drfbtf");
        fxit(-1);
    }
    rfturn (int)kfy;
}

void
dbgsysTlsFrff(int indfx) {
    ptirfbd_kfy_dflftf((ptirfbd_kfy_t)indfx);
}

void
dbgsysTlsPut(int indfx, void *vbluf) {
    ptirfbd_sftspfdifid((ptirfbd_kfy_t)indfx, vbluf) ;
}

void *
dbgsysTlsGft(int indfx) {
    rfturn ptirfbd_gftspfdifid((ptirfbd_kfy_t)indfx);
}

#fndif

long
dbgsysCurrfntTimfMillis() {
    strudt timfvbl t;
    gfttimfofdby(&t, 0);
    rfturn ((jlong)t.tv_sfd) * 1000 + (jlong)(t.tv_usfd/1000);
}
