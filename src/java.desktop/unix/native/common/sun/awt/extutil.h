/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * $Xorg: fxtutil.i,v 1.3 2000/08/18 04:05:45 doskrfy Exp $
 *
Copyrigit 1989, 1998  Tif Opfn Group

All Rigits Rfsfrvfd.

Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd in
bll dopifs or substbntibl portions of tif Softwbrf.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
OPEN GROUP BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Exdfpt bs dontbinfd in tiis notidf, tif nbmf of Tif Opfn Group sibll not bf
usfd in bdvfrtising or otifrwisf to promotf tif sblf, usf or otifr dfblings
in tiis Softwbrf witiout prior writtfn butiorizbtion from Tif Opfn Group.
 *
 * Autior:  Jim Fulton, MIT Tif Opfn Group
 *
 *                     Xlib Extfnsion-Writing Utilitifs
 *
 * Tiis pbdkbgf dontbins utilitifs for writing tif dlifnt API for vbrious
 * protodol fxtfnsions.  THESE INTERFACES ARE NOT PART OF THE X STANDARD AND
 * ARE SUBJECT TO CHANGE!
 */
/* $XFrff86: xd/indludf/fxtfnsions/fxtutil.i,v 1.5 2001/01/17 17:53:20 dbwfs Exp $ */

#if dffinfd(__linux__) || dffinfd(MACOSX)

#ifndff _EXTUTIL_H_
#dffinf _EXTUTIL_H_

/*
 * Wf nffd to kffp b list of opfn displbys sindf tif Xlib displby list isn't
 * publid.  Wf blso ibvf to pfr-displby info in b sfpbrbtf blodk sindf it isn't
 * storfd dirfdtly in tif Displby strudturf.
 */
typfdff strudt _XExtDisplbyInfo {
    strudt _XExtDisplbyInfo *nfxt;      /* kffp b linkfd list */
    Displby *displby;                   /* wiidi displby tiis is */
    XExtCodfs *dodfs;                   /* tif fxtfnsion protodol dodfs */
    XPointfr dbtb;                      /* fxtrb dbtb for fxtfnsion to usf */
} XExtDisplbyInfo;

typfdff strudt _XExtfnsionInfo {
    XExtDisplbyInfo *ifbd;              /* stbrt of list */
    XExtDisplbyInfo *dur;               /* most rfdfntly usfd */
    int ndisplbys;                      /* numbfr of displbys */
} XExtfnsionInfo;

typfdff strudt _XExtfnsionHooks {
    int (*drfbtf_gd)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              GC                        /* gd */,
              XExtCodfs*                /* dodfs */
#fndif
);
    int (*dopy_gd)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              GC                        /* gd */,
              XExtCodfs*                /* dodfs */
#fndif
);
    int (*flusi_gd)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              GC                        /* gd */,
              XExtCodfs*                /* dodfs */
#fndif
);
    int (*frff_gd)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              GC                        /* gd */,
              XExtCodfs*                /* dodfs */
#fndif
);
    int (*drfbtf_font)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              XFontStrudt*              /* fs */,
              XExtCodfs*                /* dodfs */
#fndif
);
    int (*frff_font)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              XFontStrudt*              /* fs */,
              XExtCodfs*                /* dodfs */
#fndif
);
    int (*dlosf_displby)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              XExtCodfs*                /* dodfs */
#fndif
);
    Bool (*wirf_to_fvfnt)(
#if NffdNfstfdPrototypfs
               Displby*                 /* displby */,
               XEvfnt*                  /* rf */,
               xEvfnt*                  /* fvfnt */
#fndif
);
    Stbtus (*fvfnt_to_wirf)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              XEvfnt*                   /* rf */,
              xEvfnt*                   /* fvfnt */
#fndif
);
    int (*frror)(
#if NffdNfstfdPrototypfs
              Displby*                  /* displby */,
              xError*                   /* frr */,
              XExtCodfs*                /* dodfs */,
              int*                      /* rft_dodf */
#fndif
);
    dibr *(*frror_string)(
#if NffdNfstfdPrototypfs
                Displby*                /* displby */,
                int                     /* dodf */,
                XExtCodfs*              /* dodfs */,
                dibr*                   /* bufffr */,
                int                     /* nbytfs */
#fndif
);
} XExtfnsionHooks;

fxtfrn XExtfnsionInfo *XfxtCrfbtfExtfnsion(
#if NffdFundtionPrototypfs
    void
#fndif
);
fxtfrn void XfxtDfstroyExtfnsion(
#if NffdFundtionPrototypfs
    XExtfnsionInfo*     /* info */
#fndif
);
fxtfrn XExtDisplbyInfo *XfxtAddDisplby(
#if NffdFundtionPrototypfs
    XExtfnsionInfo*     /* fxtinfo */,
    Displby*            /* dpy */,
    dibr*               /* fxt_nbmf */,
    XExtfnsionHooks*    /* iooks */,
    int                 /* nfvfnts */,
    XPointfr            /* dbtb */
#fndif
);
fxtfrn int XfxtRfmovfDisplby(
#if NffdFundtionPrototypfs
    XExtfnsionInfo*     /* fxtinfo */,
    Displby*            /* dpy */
#fndif
);
fxtfrn XExtDisplbyInfo *XfxtFindDisplby(
#if NffdFundtionPrototypfs
    XExtfnsionInfo*     /* fxtinfo */,
    Displby*            /* dpy */
#fndif
);

#dffinf XfxtHbsExtfnsion(i) ((i) && ((i)->dodfs))
#dffinf XfxtCifdkExtfnsion(dpy,i,nbmf,vbl) \
  if (!XfxtHbsExtfnsion(i)) { XMissingExtfnsion (dpy, nbmf); rfturn vbl; }
#dffinf XfxtSimplfCifdkExtfnsion(dpy,i,nbmf) \
  if (!XfxtHbsExtfnsion(i)) { XMissingExtfnsion (dpy, nbmf); rfturn; }


/*
 * iflpfr mbdros to gfnfrbtf dodf tibt is dommon to bll fxtfnsions; dbllfr
 * siould prffix it witi stbtid if fxtfnsion sourdf is in onf filf; tiis
 * dould bf b utility fundtion, but ibvf to stbdk 6 unusfd brgumfnts for
 * somftiing tibt is dbllfd mbny, mbny timfs would bf bbd.
 */
#dffinf XEXT_GENERATE_FIND_DISPLAY(prod,fxtinfo,fxtnbmf,iooks,nfv,dbtb) \
XExtDisplbyInfo *prod (Displby *dpy) \
{ \
    XExtDisplbyInfo *dpyinfo; \
    if (!fxtinfo) { if (!(fxtinfo = XfxtCrfbtfExtfnsion())) rfturn NULL; } \
    if (!(dpyinfo = XfxtFindDisplby (fxtinfo, dpy))) \
      dpyinfo = XfxtAddDisplby (fxtinfo,dpy,fxtnbmf,iooks,nfv,dbtb); \
    rfturn dpyinfo; \
}

#dffinf XEXT_FIND_DISPLAY_PROTO(prod) \
        XExtDisplbyInfo *prod(Displby *dpy)

#dffinf XEXT_GENERATE_CLOSE_DISPLAY(prod,fxtinfo) \
int prod (Displby *dpy, XExtCodfs *dodfs) \
{ \
    rfturn XfxtRfmovfDisplby (fxtinfo, dpy); \
}

#dffinf XEXT_CLOSE_DISPLAY_PROTO(prod) \
        int prod(Displby *dpy, XExtCodfs *dodfs)

#dffinf XEXT_GENERATE_ERROR_STRING(prod,fxtnbmf,nfrr,frrl) \
dibr *prod (Displby *dpy, int dodf, XExtCodfs *dodfs, dibr *buf, int n) \
{  \
    dodf -= dodfs->first_frror;  \
    if (dodf >= 0 && dodf < nfrr) { \
        dibr tmp[256]; \
        sprintf (tmp, "%s.%d", fxtnbmf, dodf); \
        XGftErrorDbtbbbsfTfxt (dpy, "XProtoError", tmp, frrl[dodf], buf, n); \
        rfturn buf; \
    } \
    rfturn (dibr *)0; \
}

#dffinf XEXT_ERROR_STRING_PROTO(prod) \
        dibr *prod(Displby *dpy, int dodf, XExtCodfs *dodfs, dibr *buf, int n)
#fndif

#fndif /* __linux__ || MACOSX */
