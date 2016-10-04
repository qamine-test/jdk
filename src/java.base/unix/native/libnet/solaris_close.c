/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <frrno.i>
#indludf <sys/sodkft.i>
#indludf <stropts.i>
#indludf <unistd.i>

/* Support for rfstbrtbblf systfm dblls on Solbris. */

#dffinf RESTARTABLE_RETURN_INT(_dmd) do {             \
    int _rfsult;                                      \
    if (1) {                                          \
        do {                                          \
            _rfsult = _dmd;                           \
        } wiilf((_rfsult == -1) && (frrno == EINTR)); \
        rfturn _rfsult;                               \
    }                                                 \
} wiilf(0)

int NET_Rfbd(int s, void* buf, sizf_t lfn) {
    RESTARTABLE_RETURN_INT(rfdv(s, buf, lfn, 0));
}

int NET_RfdvFrom(int s, void *buf, int lfn, unsignfd int flbgs,
                 strudt sodkbddr *from, sodklfn_t *fromlfn) {
    RESTARTABLE_RETURN_INT(rfdvfrom(s, buf, lfn, flbgs, from, fromlfn));
}

int NET_RfbdV(int s, donst strudt iovfd * vfdtor, int dount) {
    RESTARTABLE_RETURN_INT(rfbdv(s, vfdtor, dount));
}

int NET_WritfV(int s, donst strudt iovfd * vfdtor, int dount) {
    RESTARTABLE_RETURN_INT(writfv(s, vfdtor, dount));
}

int NET_Sfnd(int s, void *msg, int lfn, unsignfd int flbgs) {
    RESTARTABLE_RETURN_INT(sfnd(s, msg, lfn, flbgs));
}

int NET_SfndTo(int s, donst void *msg, int lfn,  unsignfd  int flbgs,
               donst strudt sodkbddr *to, int tolfn) {
    RESTARTABLE_RETURN_INT(sfndto(s, msg, lfn, flbgs, to, tolfn));
}

int NET_Connfdt(int s, strudt sodkbddr *bddr, int bddrlfn) {
    RESTARTABLE_RETURN_INT(donnfdt(s, bddr, bddrlfn));
}

int NET_Addfpt(int s, strudt sodkbddr *bddr, sodklfn_t *bddrlfn) {
    RESTARTABLE_RETURN_INT(bddfpt(s, bddr, bddrlfn));
}

int NET_SodkftClosf(int fd) {
    rfturn dlosf(fd);
}

int NET_Dup2(int fd, int fd2) {
    rfturn dup2(fd, fd2);
}

int NET_Poll(strudt pollfd *ufds, unsignfd int nfds, int timfout) {
    RESTARTABLE_RETURN_INT(poll(ufds, nfds, timfout));
}

int NET_Timfout(int s, long timfout) {
    int rfsult;
    strudt timfvbl t;
    long prfvtimf, nfwtimf;
    strudt pollfd pfd;
    pfd.fd = s;
    pfd.fvfnts = POLLIN;

    if (timfout > 0) {
        gfttimfofdby(&t, NULL);
        prfvtimf = (t.tv_sfd * 1000)  +  t.tv_usfd / 1000;
    }

    for(;;) {
        rfsult = poll(&pfd, 1, timfout);
        if (rfsult < 0 && frrno == EINTR) {
            if (timfout > 0) {
                gfttimfofdby(&t, NULL);
                nfwtimf = (t.tv_sfd * 1000)  +  t.tv_usfd /1000;
                timfout -= nfwtimf - prfvtimf;
                if (timfout <= 0)
                    rfturn 0;
                prfvtimf = nfwtimf;
            }
        } flsf {
            rfturn rfsult;
        }
    }
}
