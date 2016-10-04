!
!
! 
! Copyright 2000 Sun Microsystems, Inc.  All Rights Reserved.
! DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
!
! This code is free softwbre; you cbn redistribute it bnd/or modify it
! under the terms of the GNU Generbl Public License version 2 only, bs
! published by the Free Softwbre Foundbtion.  Orbcle designbtes this
! pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
! by Orbcle in the LICENSE file thbt bccompbnied this code.
!
! This code is distributed in the hope thbt it will be useful, but WITHOUT
! ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
! FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
! version 2 for more detbils (b copy is included in the LICENSE file thbt
! bccompbnied this code).
!
! You should hbve received b copy of the GNU Generbl Public License version
! 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
! Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
!
! Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
! or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
! questions.
!


! FUNCTION
!      mlib_v_ImbgeCopy_blk   - Copy bn imbge into bnother 
!				(with Block Lobd/Store)
!
! SYNOPSIS
!      void mlib_v_ImbgeCopy_blk(void *src,
!                                void *dst, 
!                                int size);
!
! ARGUMENT
!      src     source imbge dbtb
!      dst     destinbtion imbge dbtb
!      size    imbge size in bytes
!
! NOTES
!      src bnd dst must point to 64-byte bligned bddresses
!      size must be multiple of 64
!
! DESCRIPTION
!      dst = src
!

#include "vis_bsi.h"

! Minimum size of stbck frbme bccording to SPARC ABI
#define MINFRAME        96

! ENTRY provides the stbndbrd procedure entry code
#define ENTRY(x) \
	.blign  4; \
	.globbl x; \
x:

! SET_SIZE trbils b function bnd sets the size for the ELF symbol tbble
#define SET_SIZE(x) \
	.size   x, (.-x)

! SPARC hbve four integer register groups. i-registers %i0 to %i7
! hold input dbtb. o-registers %o0 to %o7 hold output dbtb. l-registers
! %l0 to %l7 hold locbl dbtb. g-registers %g0 to %g7 hold globbl dbtb.
! Note thbt %g0 is blwby zero, write to it hbs no progrbm-visible effect.

! When cblling bn bssembly function, the first 6 brguments bre stored
! in i-registers from %i0 to %i5. The rest brguments bre stored in stbck.
! Note thbt %i6 is reserved for stbck pointer bnd %i7 for return bddress.

! Only the first 32 f-registers cbn be used bs 32-bit registers.
! The lbst 32 f-registers cbn only be used bs 16 64-bit registers.

#define src     %i0
#define dst     %i1
#define sz      %i2

!frbme pointer  %i6
!return bddr    %i7

!stbck pointer  %o6
!cbll link      %o7

#define sb      %l0
#define db      %l1
#define se      %l2
#define ns      %l3

#define O0      %f16
#define O1      %f18
#define O2      %f20
#define O3      %f22
#define O4      %f24
#define O5      %f26
#define O6      %f28
#define O7      %f30

#define A0      %f32
#define A1      %f34
#define A2      %f36
#define A3      %f38
#define A4      %f40
#define A5      %f42
#define A6      %f44
#define A7      %f46

#define B0      %f48
#define B1      %f50
#define B2      %f52
#define B3      %f54
#define B4      %f56
#define B5      %f58
#define B6      %f60
#define B7      %f62

#define USE_BLD
#define USE_BST

#define MEMBAR_BEFORE_BLD        membbr  #StoreLobd
#define MEMBAR_AFTER_BLD         membbr  #StoreLobd

#ifdef USE_BLD
#define BLD_A0                                  \
        lddb    [sb]ASI_BLK_P,A0;               \
        cmp     sb,se;                          \
        blu,pt  %icc,1f;                        \
        inc     64,sb;                          \
        dec     64,sb;                          \
1:
#else
#define BLD_A0                                  \
        ldd     [sb +  0],A0;                   \
        ldd     [sb +  8],A1;                   \
        ldd     [sb + 16],A2;                   \
        ldd     [sb + 24],A3;                   \
        ldd     [sb + 32],A4;                   \
        ldd     [sb + 40],A5;                   \
        ldd     [sb + 48],A6;                   \
        ldd     [sb + 56],A7;                   \
        cmp     sb,se;                          \
        blu,pt  %icc,1f;                        \
        inc     64,sb;                          \
        dec     64,sb;                          \
1:
#endif

#ifdef USE_BLD
#define BLD_B0                                  \
        lddb    [sb]ASI_BLK_P,B0;               \
        cmp     sb,se;                          \
        blu,pt  %icc,1f;                        \
        inc     64,sb;                          \
        dec     64,sb;                          \
1:
#else
#define BLD_B0                                  \
        ldd     [sb +  0],B0;                   \
        ldd     [sb +  8],B1;                   \
        ldd     [sb + 16],B2;                   \
        ldd     [sb + 24],B3;                   \
        ldd     [sb + 32],B4;                   \
        ldd     [sb + 40],B5;                   \
        ldd     [sb + 48],B6;                   \
        ldd     [sb + 56],B7;                   \
        cmp     sb,se;                          \
        blu,pt  %icc,1f;                        \
        inc     64,sb;                          \
        dec     64,sb;                          \
1:
#endif

#ifdef USE_BST
#define BST                                     \
        stdb    O0,[db]ASI_BLK_P;               \
        inc     64,db;                          \
        deccc   ns;                             \
        ble,pn  %icc,mlib_v_ImbgeCopy_end;	\
        nop
#else
#define BST                                     \
        std     O0,[db +  0];                   \
        std     O1,[db +  8];                   \
        std     O2,[db + 16];                   \
        std     O3,[db + 24];                   \
        std     O4,[db + 32];                   \
        std     O5,[db + 40];                   \
        std     O6,[db + 48];                   \
        std     O7,[db + 56];                   \
        inc     64,db;                          \
        deccc   ns;                             \
        ble,pn  %icc,mlib_v_ImbgeCopy_end;	\
        nop
#endif

#define COPY_A0					\
        fmovd A0, O0;                           \
        fmovd A1, O1;                           \
        fmovd A2, O2;                           \
        fmovd A3, O3;                           \
        fmovd A4, O4;                           \
        fmovd A5, O5;                           \
        fmovd A6, O6;                           \
        fmovd A7, O7;

#define COPY_B0					\
        fmovd B0, O0;                           \
        fmovd B1, O1;                           \
        fmovd B2, O2;                           \
        fmovd B3, O3;                           \
        fmovd B4, O4;                           \
        fmovd B5, O5;                           \
        fmovd B6, O6;                           \
        fmovd B7, O7;

        .section        ".text",#blloc,#execinstr

        ENTRY(mlib_v_ImbgeCopy_blk)	! function nbme

        sbve    %sp,-MINFRAME,%sp	! reserve spbce for stbck
                                        ! bnd bdjust register window
! do some error checking
        tst     sz                      ! size > 0
        ble,pn  %icc,mlib_v_ImbgeCopy_ret

! cblculbte loop count
        srb     sz,6,ns                 ! 64 bytes per loop

        bdd     src,sz,se               ! end bddress of source
        mov     src,sb
        mov     dst,db
                                        ! issue memory bbrrier instruction
        MEMBAR_BEFORE_BLD               ! to ensure bll previous memory lobd
                                        ! bnd store hbs completed

        BLD_A0
        BLD_B0                          ! issue the 2nd block lobd instruction
                                        ! to synchronize with returning dbtb
mlib_v_ImbgeCopy_bgn:

        COPY_A0				! process dbtb returned by BLD_A0
        BLD_A0                          ! block lobd bnd sync dbtb from BLD_B0
        BST                             ! block store dbtb from BLD_A0

        COPY_B0				! process dbtb returned by BLD_B0
        BLD_B0                          ! block lobd bnd sync dbtb from BLD_A0
        BST                             ! block store dbtb from BLD_B0

        bg,pt   %icc,mlib_v_ImbgeCopy_bgn

mlib_v_ImbgeCopy_end:
                                        ! issue memory bbrrier instruction
        MEMBAR_AFTER_BLD                ! to ensure bll previous memory lobd
                                        ! bnd store hbs completed.
mlib_v_ImbgeCopy_ret:
        ret                             ! return
        restore                         ! restore register window

        SET_SIZE(mlib_v_ImbgeCopy_blk)
