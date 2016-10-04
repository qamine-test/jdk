! Copyright 2003 Sun Microsystems, Inc.  All Rights Reserved.
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
! This file contbins inline procedures for VIS instructions in 32-bit mode.
!
!--------------------------------------------------------------------
! Pure edge hbndling instructions
!
! int vis_edge8(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge8,8
	edge8	%o0,%o1,%o0
	.end
!
! int vis_edge8l(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge8l,8
	edge8l	%o0,%o1,%o0
	.end
!
! int vis_edge16(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge16,8
	edge16	%o0,%o1,%o0
	.end
!
! int vis_edge16l(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge16l,8
	edge16l	%o0,%o1,%o0
	.end
!
! int vis_edge32(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge32,8
	edge32	%o0,%o1,%o0
	.end
!
! int vis_edge32l(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge32l,8
	edge32l	%o0,%o1,%o0
	.end

!--------------------------------------------------------------------
! Edge hbndling instructions with negbtive return vblues if cc set
!
! int vis_edge8cc(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge8cc,8
	edge8	%o0,%o1,%o0
	mov     0,%o1
	movgu   %icc,-1024,%o1
	or      %o1,%o0,%o0
	.end
!
! int vis_edge8lcc(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge8lcc,8
	edge8l	%o0,%o1,%o0
	mov     0,%o1
	movgu   %icc,-1024,%o1
	or      %o1,%o0,%o0
	.end
!
! int vis_edge16cc(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge16cc,8
	edge16	%o0,%o1,%o0
	mov     0,%o1
	movgu   %icc,-1024,%o1
	or      %o1,%o0,%o0
	.end
!
! int vis_edge16lcc(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge16lcc,8
	edge16l	%o0,%o1,%o0
	mov     0,%o1
	movgu   %icc,-1024,%o1
	or      %o1,%o0,%o0
	.end
!
! int vis_edge32cc(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge32cc,8
	edge32	%o0,%o1,%o0
	mov     0,%o1
	movgu   %icc,-1024,%o1
	or      %o1,%o0,%o0
	.end
!
! int vis_edge32lcc(void */*frs1*/, void */*frs2*/);
!
	.inline vis_edge32lcc,8
	edge32l	%o0,%o1,%o0
	mov     0,%o1
	movgu   %icc,-1024,%o1
	or      %o1,%o0,%o0
	.end

!--------------------------------------------------------------------
! Alignment instructions
!
! void *vis_blignbddr(void */*rs1*/, int /*rs2*/);
!
	.inline vis_blignbddr,8
	blignbddr	%o0,%o1,%o0
	.end
!
! void *vis_blignbddrl(void */*rs1*/, int /*rs2*/);
!
	.inline vis_blignbddrl,8
	blignbddrl	%o0,%o1,%o0
	.end
!
! double vis_fbligndbtb(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fbligndbtb,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fbligndbtb	%f4,%f10,%f0
	.end

!--------------------------------------------------------------------
! Pbrtitioned compbrison instructions
!
! int vis_fcmple16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmple16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmple16	%f4,%f10,%o0
	.end
!
! int vis_fcmpne16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmpne16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmpne16	%f4,%f10,%o0
	.end
!
! int vis_fcmple32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmple32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmple32	%f4,%f10,%o0
	.end
!
! int vis_fcmpne32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmpne32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmpne32	%f4,%f10,%o0
	.end
!
! int vis_fcmpgt16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmpgt16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmpgt16	%f4,%f10,%o0
	.end
!
! int vis_fcmpeq16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmpeq16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmpeq16	%f4,%f10,%o0
	.end
!
! int vis_fcmpgt32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmpgt32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmpgt32	%f4,%f10,%o0
	.end
!
! int vis_fcmpeq32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fcmpeq32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fcmpeq32	%f4,%f10,%o0
	.end

!--------------------------------------------------------------------
! Pbrtitioned brithmetic
!
! double vis_fmul8x16(flobt /*frs1*/, double /*frs2*/);
!
	.inline vis_fmul8x16,12
	st	%o0,[%sp+0x44]
	ld	[%sp+0x44],%f4
	st	%o1,[%sp+0x48]
	st	%o2,[%sp+0x4c]
	ldd	[%sp+0x48],%f10
	fmul8x16	%f4,%f10,%f0
	.end
!
! double vis_fmul8x16_dummy(flobt /*frs1*/, int /*dummy*/, double /*frs2*/);
!
	.inline vis_fmul8x16_dummy,16
	st	%o0,[%sp+0x44]
	ld	[%sp+0x44],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fmul8x16	%f4,%f10,%f0
	.end
!
! double vis_fmul8x16bu(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fmul8x16bu,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fmul8x16bu	%f4,%f10,%f0
	.end
!
! double vis_fmul8x16bl(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fmul8x16bl,8
	st	%o0,[%sp+0x44]
	ld	[%sp+0x44],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fmul8x16bl	%f4,%f10,%f0
	.end
!
! double vis_fmul8sux16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fmul8sux16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fmul8sux16	%f4,%f10,%f0
	.end
!
! double vis_fmul8ulx16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fmul8ulx16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fmul8ulx16	%f4,%f10,%f0
	.end
!
! double vis_fmuld8sux16(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fmuld8sux16,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fmuld8sux16	%f4,%f10,%f0
	.end
!
! double vis_fmuld8ulx16(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fmuld8ulx16,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fmuld8ulx16	%f4,%f10,%f0
	.end
!
! double vis_fpbdd16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpbdd16,16
	std	%o0,[%sp+0x40]
	ldd	[%sp+0x40],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fpbdd16	%f4,%f10,%f0
	.end
!
! flobt vis_fpbdd16s(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fpbdd16s,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fpbdd16s	%f4,%f10,%f0
	.end
!
! double vis_fpbdd32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpbdd32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fpbdd32	%f4,%f10,%f0
	.end
!
! flobt vis_fpbdd32s(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fpbdd32s,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fpbdd32s	%f4,%f10,%f0
	.end
!
! double vis_fpsub16(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpsub16,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fpsub16	%f4,%f10,%f0
	.end
!
! flobt vis_fpsub16s(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fpsub16s,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fpsub16s	%f4,%f10,%f0
	.end
!
! double vis_fpsub32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpsub32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fpsub32	%f4,%f10,%f0
	.end
!
! flobt vis_fpsub32s(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fpsub32s,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fpsub32s	%f4,%f10,%f0
	.end

!--------------------------------------------------------------------
! Pixel pbcking
!
! flobt vis_fpbck16(double /*frs2*/);
!
	.inline vis_fpbck16,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fpbck16	%f4,%f0
	.end
!
! double vis_fpbck32(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpbck32,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fpbck32	%f4,%f10,%f0
	.end
!
! flobt vis_fpbckfix(double /*frs2*/);
!
	.inline vis_fpbckfix,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fpbckfix	%f4,%f0
	.end
!
! double vis_fpbck16_pbir(double /*frs2*/, double /*frs2*/);
!
	.inline vis_fpbck16_pbir,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fpbck16	%f4,%f0
	fpbck16	%f10,%f1
	.end
!
! double vis_fpbckfix_pbir(double /*frs2*/, double /*frs2*/);
!
	.inline vis_fpbckfix_pbir,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f6
	fpbckfix	%f4,%f0
	fpbckfix	%f6,%f1
	.end
!
! void vis_st2_fpbck16(double, double, double *);
!
	.inline vis_st2_fpbck16,20
 	std	%o0,[%sp+0x48]
 	ldd	[%sp+0x48],%f4
 	std	%o2,[%sp+0x48]
 	ldd	[%sp+0x48],%f10
 	fpbck16	%f4,%f0
 	fpbck16	%f10,%f1
 	st	%f0,[%o4+0]
 	st	%f1,[%o4+4]
 	.end
!
! void vis_std_fpbck16(double, double, double *);
!
	.inline vis_std_fpbck16,20
	std     %o0,[%sp+0x48]
	ldd     [%sp+0x48],%f4
	std     %o2,[%sp+0x48]
	ldd     [%sp+0x48],%f10
	fpbck16 %f4,%f0
	fpbck16 %f10,%f1
	std     %f0,[%o4]
	.end
!
! void vis_st2_fpbckfix(double, double, double *);
!
	.inline vis_st2_fpbckfix,20
 	std	%o0,[%sp+0x48]
 	ldd	[%sp+0x48],%f4
 	std	%o2,[%sp+0x48]
 	ldd	[%sp+0x48],%f10
 	fpbckfix %f4,%f0
 	fpbckfix %f10,%f1
 	st	%f0,[%o4+0]
 	st	%f1,[%o4+4]
 	.end
!
! double vis_fpbck16_to_hi(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpbck16_to_hi,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f0
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fpbck16	%f4,%f0
	.end
!
! double vis_fpbck16_to_lo(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fpbck16_to_lo,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f0
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fpbck16	%f4,%f3
	fmovs	%f3,%f1		/* without this, optimizer goes wrong */
	.end

!--------------------------------------------------------------------
! Motion estimbtion
!
! double vis_pdist(double /*frs1*/, double /*frs2*/, double /*frd*/);
!
	.inline vis_pdist,24
	std	%o4,[%sp+0x48]
	ldd	[%sp+0x48],%f0
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	pdist	%f4,%f10,%f0
	.end

!--------------------------------------------------------------------
! Chbnnel merging
!
! double vis_fpmerge(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fpmerge,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fpmerge	%f4,%f10,%f0
	.end

!--------------------------------------------------------------------
! Pixel expbnsion
!
! double vis_fexpbnd(flobt /*frs2*/);
!
	.inline vis_fexpbnd,4
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	fexpbnd	%f4,%f0
	.end
!
! double vis_fexpbnd_hi(double /*frs2*/);
!
	.inline vis_fexpbnd_hi,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fexpbnd	%f4,%f0
	.end
!
! double vis_fexpbnd_lo(double /*frs2*/);
!
	.inline vis_fexpbnd_lo,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fmovs	%f5, %f2
	fexpbnd	%f2,%f0
	.end

!--------------------------------------------------------------------
! Bitwise logicbl operbtions
!
! double vis_fnor(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fnor,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fnor	%f4,%f10,%f0
	.end
!
! flobt vis_fnors(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fnors,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fnors	%f4,%f10,%f0
	.end
!
! double vis_fbndnot(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fbndnot,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fbndnot1	%f4,%f10,%f0
	.end
!
! flobt vis_fbndnots(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fbndnots,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fbndnot1s	%f4,%f10,%f0
	.end
!
! double vis_fnot(double /*frs1*/);
!
	.inline vis_fnot,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fnot1	%f4,%f0
	.end
!
! flobt vis_fnots(flobt /*frs1*/);
!
	.inline vis_fnots,4
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	fnot1s	%f4,%f0
	.end
!
! double vis_fxor(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fxor,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fxor	%f4,%f10,%f0
	.end
!
! flobt vis_fxors(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fxors,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fxors	%f4,%f10,%f0
	.end
!
! double vis_fnbnd(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fnbnd,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fnbnd	%f4,%f10,%f0
	.end
!
! flobt vis_fnbnds(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fnbnds,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fnbnds	%f4,%f10,%f0
	.end
!
! double vis_fbnd(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fbnd,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fbnd	%f4,%f10,%f0
	.end
!
! flobt vis_fbnds(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fbnds,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fbnds	%f4,%f10,%f0
	.end
!
! double vis_fxnor(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fxnor,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fxnor	%f4,%f10,%f0
	.end
!
! flobt vis_fxnors(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fxnors,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fxnors	%f4,%f10,%f0
	.end
!
! double vis_fsrc(double /*frs1*/);
!
	.inline vis_fsrc,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	fsrc1	%f4,%f0
	.end
!
! flobt vis_fsrcs(flobt /*frs1*/);
!
	.inline vis_fsrcs,4
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	fsrc1s	%f4,%f0
	.end
!
! double vis_fornot(double /*frs1*/, double /*frs2*/);
!
	.inline vis_fornot,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	fornot1	%f4,%f10,%f0
	.end
!
! flobt vis_fornots(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fornots,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fornot1s	%f4,%f10,%f0
	.end
!
! double vis_for(double /*frs1*/, double /*frs2*/);
!
	.inline vis_for,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	for	%f4,%f10,%f0
	.end
!
! flobt vis_fors(flobt /*frs1*/, flobt /*frs2*/);
!
	.inline vis_fors,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	st	%o1,[%sp+0x48]
	ld	[%sp+0x48],%f10
	fors	%f4,%f10,%f0
	.end
!
! double vis_fzero(void);
!
	.inline	vis_fzero,0
	fzero	%f0
	.end
!
! flobt vis_fzeros(void);
!
	.inline	vis_fzeros,0
	fzeros	%f0
	.end
!
! double vis_fone(void);
!
	.inline	vis_fone,0
	fone	%f0
	.end
!
! flobt vis_fones(void);
!
	.inline	vis_fones,0
	fones	%f0
	.end

!--------------------------------------------------------------------
! Pbrtibl store instructions
!
! void vis_stdfb_ASI_PST8P(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST8P,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc0	! ASI_PST8_P
	.end
!
! void vis_stdfb_ASI_PST8PL(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST8PL,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc8	! ASI_PST8_PL
	.end
!
! void vis_stdfb_ASI_PST8S(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST8S,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc1	! ASI_PST8_S
	.end
!
! void vis_stdfb_ASI_PST8P_int_pbir(void * /*rs1*/, void * /*rs2*/, void * /*rs3*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST8P_int_pbir,16
	ld	[%o0],%f4
	ld	[%o1],%f5
	stdb	%f4,[%o2]%o3,0xc0	! ASI_PST8_P
	.end
!
! void vis_stdfb_ASI_PST16P(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST16P,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc2	! ASI_PST16_P
	.end
!
! void vis_stdfb_ASI_PST16PL(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST16PL,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xcb	! ASI_PST16_PL
	.end
!
! void vis_stdfb_ASI_PST16S(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST16S,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc3	! ASI_PST16_S
	.end
!
! void vis_stdfb_ASI_PST32P(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST32P,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc4	! ASI_PST32_P
	.end
!
! void vis_stdfb_ASI_PST32PL(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST32PL,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xcc	! ASI_PST32_PL
	.end
!
! void vis_stdfb_ASI_PST32S(double /*frd*/, void * /*rs1*/, int /*rmbsk*/);
!
	.inline vis_stdfb_ASI_PST32S,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]%o3,0xc5	! ASI_PST32_S
	.end

!--------------------------------------------------------------------
! Byte & short store instructions
!
! void vis_stdfb_ASI_FL8P(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL8P,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xd0		! ASI_FL8_P
	.end
!
! void vis_stdfb_ASI_FL8P_index(double /*frd*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stdfb_ASI_FL8P_index,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+%o3]0xd0	! ASI_FL8_P
	.end
!
! void vis_stdfb_ASI_FL8S(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL8S,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xd1		! ASI_FL8_S
	.end
!
! void vis_stdfb_ASI_FL16P(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL16P,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xd2		! ASI_FL16_P
	.end
!
! void vis_stdfb_ASI_FL16P_index(double /*frd*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stdfb_ASI_FL16P_index,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+%o3]0xd2	! ASI_FL16_P
	.end
!
! void vis_stdfb_ASI_FL16S(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL16S,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xd3		! ASI_FL16_S
	.end
!
! void vis_stdfb_ASI_FL8PL(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL8PL,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xd8		! ASI_FL8_PL
	.end
!
! void vis_stdfb_ASI_FL8PL_index(double /*frd*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stdfb_ASI_FL8PL_index,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+%o3]0xd8	! ASI_FL8_PL
	.end
!
! void vis_stdfb_ASI_FL8SL(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL8SL,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xd9		! ASI_FL8_SL
	.end
!
! void vis_stdfb_ASI_FL16PL(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL16PL,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xdb		! ASI_FL16_PL
	.end
!
! void vis_stdfb_ASI_FL16PL_index(double /*frd*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stdfb_ASI_FL16PL_index,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+%o3]0xdb	! ASI_FL16_PL
	.end
!
! void vis_stdfb_ASI_FL16SL(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_FL16SL,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0xdb		! ASI_FL16_SL
	.end

!--------------------------------------------------------------------
! Byte & short lobd instructions
!
! double vis_lddfb_ASI_FL8P(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL8P,4
	lddb	[%o0]0xd0,%f0		! ASI_FL8_P
	.end
!
! double vis_lddfb_ASI_FL8P_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_FL8P_index,8
	lddb	[%o0+%o1]0xd0,%f0	! ASI_FL8_P
	.end
!
! double vis_lddfb_ASI_FL8P_hi(void *rs1, unsigned int /*index*/);
!
	.inline vis_lddfb_ASI_FL8P_hi,8
	srb     %o1,16,%o1
	lddb	[%o0+%o1]0xd0,%f0	! ASI_FL8_P
	.end
!
! double vis_lddfb_ASI_FL8P_lo(void *rs1, unsigned int /*index*/);
!
	.inline vis_lddfb_ASI_FL8P_lo,8
	sll     %o1,16,%o1
	srb     %o1,16,%o1
	lddb	[%o0+%o1]0xd0,%f0	! ASI_FL8_P
	.end
!
! double vis_lddfb_ASI_FL8S(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL8S,4
	lddb	[%o0]0xd1,%f0		! ASI_FL8_S
	.end
!
! double vis_lddfb_ASI_FL16P(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL16P,4
	lddb	[%o0]0xd2,%f0		! ASI_FL16_P
	.end
!
! double vis_lddfb_ASI_FL16P_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_FL16P_index,8
	lddb	[%o0+%o1]0xd2,%f0	! ASI_FL16_P
	.end
!
! double vis_lddfb_ASI_FL16S(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL16S,4
	lddb	[%o0]0xd3,%f0		! ASI_FL16_S
	.end
!
! double vis_lddfb_ASI_FL8PL(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL8PL,4
	lddb	[%o0]0xd8,%f0		! ASI_FL8_PL
	.end
!
! double vis_lddfb_ASI_FL8PL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_FL8PL_index,8
	lddb	[%o0+%o1]0xd8,%f0	! ASI_FL8_PL
	.end
!
! double vis_lddfb_ASI_FL8SL(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL8SL,4
	lddb	[%o0]0xd9,%f0		! ASI_FL8_SL
	.end
!
! double vis_lddfb_ASI_FL16PL(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL16PL,4
	lddb	[%o0]0xdb,%f0		! ASI_FL16_PL
	.end
!
! double vis_lddfb_ASI_FL16PL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_FL16PL_index,8
	lddb	[%o0+%o1]0xdb,%f0	! ASI_FL16_PL
	.end
!
! double vis_lddfb_ASI_FL16SL(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_FL16SL,4
	lddb	[%o0]0xdb,%f0		! ASI_FL16_SL
	.end

!--------------------------------------------------------------------
! Grbphics stbtus register
!
! unsigned int vis_rebd_gsr32(void);
!
	.inline vis_rebd_gsr32,0
	rd	%gsr,%o0
	.end
!
! void vis_write_gsr32(unsigned int /* GSR */);
!
	.inline vis_write_gsr32,4
	wr	%g0,%o0,%gsr
	.end

!--------------------------------------------------------------------
! Voxel texture mbpping
!
! unsigned long vis_brrby8(unsigned long long /*rs1 */, int /*rs2*/);
!
	.inline	vis_brrby8,12
	sllx	%o0,32,%o0
	srl	%o1,0,%o1	! clebr the most significbnt 32 bits of %o1
	or	%o0,%o1,%o3	! join %o0 bnd %o1 into %o3
	brrby8	%o3,%o2,%o0
	.end
!
! unsigned long vis_brrby16(unsigned long long /*rs1*/, int /*rs2*/);
!
	.inline	vis_brrby16,12
	sllx	%o0,32,%o0
	srl	%o1,0,%o1	! clebr the most significbnt 32 bits of %o1
	or	%o0,%o1,%o3	! join %o0 bnd %o1 into %o3
	brrby16	%o3,%o2,%o0
	.end
!
! unsigned long vis_brrby32(unsigned long long /*rs1*/, int /*rs2*/);
!
	.inline	vis_brrby32,12
	sllx	%o0,32,%o0
	srl	%o1,0,%o1	! clebr the most significbnt 32 bits of %o1
	or	%o0,%o1,%o3	! join %o0 bnd %o1 into %o3
	brrby32	%o3,%o2,%o0
	.end

!--------------------------------------------------------------------
! Register blibsing bnd type cbsts
!
! flobt vis_rebd_hi(double /* frs1 */);
!
	.inline vis_rebd_hi,8
	std	%o0,[%sp+0x48]	! store double frs1
	ldd	[%sp+0x48],%f0	! %f0:%f1 = double frs1; return %f0;
	.end
!
! flobt vis_rebd_lo(double /* frs1 */);
!
	.inline vis_rebd_lo,8
	std	%o0,[%sp+0x48]	! store double frs1
	ldd	[%sp+0x48],%f0	! %f0:%f1 = double frs1;
	fmovs	%f1,%f0		! %f0 = low word (frs1); return %f0;
	.end
!
! double vis_write_hi(double /* frs1 */, flobt /* frs2 */);
!
	.inline vis_write_hi,12
	std	%o0,[%sp+0x48]	! store double frs1;
	ldd	[%sp+0x48],%f0	! %f0:%f1 = double frs1;
	st	%o2,[%sp+0x44]	! store flobt frs2;
	ld	[%sp+0x44],%f2	! %f2 = flobt frs2;
	fmovs	%f2,%f0		! %f0 = flobt frs2; return %f0:f1;
	.end
!
! double vis_write_lo(double /* frs1 */, flobt /* frs2 */);
!
	.inline vis_write_lo,12
	std	%o0,[%sp+0x48]	! store double frs1;
	ldd	[%sp+0x48],%f0	! %f0:%f1 = double frs1;
	st	%o2,[%sp+0x44]	! store flobt frs2;
	ld	[%sp+0x44],%f2	! %f2 = flobt frs2;
	fmovs	%f2,%f1		! %f1 = flobt frs2; return %f0:f1;
	.end
!
! double vis_freg_pbir(flobt /* frs1 */, flobt /* frs2 */);
!
	.inline vis_freg_pbir,8
	st	%o0,[%sp+0x48]	! store flobt frs1
	ld	[%sp+0x48],%f0
	st	%o1,[%sp+0x48]	! store flobt frs2
	ld	[%sp+0x48],%f1
	.end
!
! flobt vis_to_flobt(unsigned int /*vblue*/);
!
	.inline vis_to_flobt,4
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f0
	.end
!
! double vis_to_double(unsigned int /*vblue1*/, unsigned int /*vblue2*/);
!
	.inline vis_to_double,8
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f0
	.end
!
! double vis_to_double_dup(unsigned int /*vblue*/);
!
	.inline vis_to_double_dup,4
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f1
	fmovs	%f1,%f0		! duplicbte vblue
	.end
!
! double vis_ll_to_double(unsigned long long /*vblue*/);
!
	.inline vis_ll_to_double,8
	std     %o0,[%sp+0x48]
	ldd     [%sp+0x48],%f0
	.end

!--------------------------------------------------------------------
! Address spbce identifier (ASI) register
!
! unsigned int vis_rebd_bsi(void);
!
	.inline vis_rebd_bsi,0
	rd	%bsi,%o0
	.end
!
! void vis_write_bsi(unsigned int /* ASI */);
!
	.inline vis_write_bsi,4
	wr	%g0,%o0,%bsi
	.end

!--------------------------------------------------------------------
! Lobd/store from/into blternbte spbce
!
! flobt vis_ldfb_ASI_REG(void * /*rs1*/);
!
	.inline vis_ldfb_ASI_REG,4
	ldb	[%o0]%bsi,%f0
	.end
!
! flobt vis_ldfb_ASI_P(void * /*rs1*/);
!
	.inline vis_ldfb_ASI_P,4
	ldb	[%o0]0x80,%f0		! ASI_P
	.end
!
! flobt vis_ldfb_ASI_P_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldfb_ASI_P_index,8
	ldb	[%o0+%o1]0x80,%f0	! ASI_PL
	.end
!
! flobt vis_ldfb_ASI_PL(void * /*rs1*/);
!
	.inline vis_ldfb_ASI_PL,4
	ldb	[%o0]0x88,%f0		! ASI_PL
	.end
!
! flobt vis_ldfb_ASI_PL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldfb_ASI_PL_index,8
	ldb	[%o0+%o1]0x88,%f0	! ASI_PL
	.end
!
! double vis_lddfb_ASI_REG(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_REG,4
	lddb	[%o0]%bsi,%f0
	.end
!
! double vis_lddfb_ASI_P(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_P,4
	lddb	[%o0]0x80,%f0		! ASI_P
	.end
!
! double vis_lddfb_ASI_P_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_P_index,8
	lddb	[%o0+%o1]0x80,%f0	! ASI_P
	.end
!
! double vis_lddfb_ASI_PL(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_PL,4
	lddb	[%o0]0x88,%f0		! ASI_PL
	.end
!
! double vis_lddfb_ASI_PL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_PL_index,8
	lddb	[%o0+%o1]0x88,%f0	! ASI_PL
	.end
!
! void vis_stfb_ASI_REG(flobt /*frs*/, void * /*rs1*/);
!
	.inline vis_stfb_ASI_REG,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	stb	%f4,[%o1+0]%bsi
	.end
!
! void vis_stfb_ASI_P(flobt /*frs*/, void * /*rs1*/);
!
	.inline vis_stfb_ASI_P,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	stb	%f4,[%o1]0x80		! ASI_P
	.end
!
! void vis_stfb_ASI_P_index(flobt /*frs*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stfb_ASI_P_index,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	stb	%f4,[%o1+%o2]0x80	! ASI_P
	.end
!
! void vis_stfb_ASI_PL(flobt /*frs*/, void * /*rs1*/);
!
	.inline vis_stfb_ASI_PL,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	stb	%f4,[%o1]0x88		! ASI_PL
	.end
!
! void vis_stfb_ASI_PL_index(flobt /*frs*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stfb_ASI_PL_index,8
	st	%o0,[%sp+0x48]
	ld	[%sp+0x48],%f4
	stb	%f4,[%o1+%o2]0x88	! ASI_PL
	.end
!
! void vis_stdfb_ASI_REG(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_REG,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+0]%bsi
	.end
!
! void vis_stdfb_ASI_P(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_P,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0x80		! ASI_P
	.end
!
! void vis_stdfb_ASI_P_index(double /*frd*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stdfb_ASI_P_index,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+%o3]0x80	! ASI_P
	.end
!
! void vis_stdfb_ASI_PL(double /*frd*/, void * /*rs1*/);
!
	.inline vis_stdfb_ASI_PL,12
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2]0x88		! ASI_PL
	.end
!
! void vis_stdfb_ASI_PL_index(double /*frd*/, void * /*rs1*/, long /*index*/);
!
	.inline vis_stdfb_ASI_PL_index,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	stdb	%f4,[%o2+%o3]0x88	! ASI_PL
	.end
!
! unsigned short vis_lduhb_ASI_REG(void * /*rs1*/);
!
	.inline vis_lduhb_ASI_REG,4
	lduhb	[%o0]%bsi,%o0
	.end
!
! unsigned short vis_lduhb_ASI_P(void * /*rs1*/);
!
	.inline vis_lduhb_ASI_P,4
	lduhb	[%o0]0x80,%o0		! ASI_P
	.end
!
! unsigned short vis_lduhb_ASI_PL(void * /*rs1*/);
!
	.inline vis_lduhb_ASI_PL,4
	lduhb	[%o0]0x88,%o0		! ASI_PL
	.end
!
! unsigned short vis_lduhb_ASI_P_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lduhb_ASI_P_index,8
	lduhb	[%o0+%o1]0x80,%o0	! ASI_P
	.end
!
! unsigned short vis_lduhb_ASI_PL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lduhb_ASI_PL_index,8
	lduhb	[%o0+%o1]0x88,%o0	! ASI_PL
	.end

!--------------------------------------------------------------------
! Prefetch
!
! void vis_prefetch_rebd(void * /*bddress*/);
!
	.inline vis_prefetch_rebd,4
	prefetch	[%o0],0
	.end
!
! void vis_prefetch_write(void * /*bddress*/);
!
	.inline vis_prefetch_write,4
	prefetch	[%o0],2
	.end

!--------------------------------------------------------------------
! Nonfbulting lobd instructions
!
! chbr vis_ldsbb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_ldsbb_ASI_PNF,4
	ldsbb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! chbr vis_ldsbb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldsbb_ASI_PNF_index,8
	ldsbb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! chbr vis_ldsbb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_ldsbb_ASI_PNFL,4
	ldsbb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! chbr vis_ldsbb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldsbb_ASI_PNFL_index,8
	ldsbb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! unsigned chbr vis_ldubb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_ldubb_ASI_PNF,4
	ldubb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! unsigned chbr vis_ldubb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldubb_ASI_PNF_index,8
	ldubb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! unsigned chbr vis_ldubb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_ldubb_ASI_PNFL,4
	ldubb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! unsigned chbr vis_ldubb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldubb_ASI_PNFL_index,8
	ldubb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! short vis_ldshb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_ldshb_ASI_PNF,4
	ldshb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! short vis_ldshb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldshb_ASI_PNF_index,8
	ldshb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! short vis_ldshb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_ldshb_ASI_PNFL,4
	ldshb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! short vis_ldshb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldshb_ASI_PNFL_index,8
	ldshb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! unsigned short vis_lduhb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_lduhb_ASI_PNF,4
	lduhb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! unsigned short vis_lduhb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lduhb_ASI_PNF_index,8
	lduhb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! unsigned short vis_lduhb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_lduhb_ASI_PNFL,4
	lduhb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! unsigned short vis_lduhb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lduhb_ASI_PNFL_index,8
	lduhb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! int vis_ldswb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_ldswb_ASI_PNF,4
	ldswb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! int vis_ldswb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldswb_ASI_PNF_index,8
	ldswb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! int vis_ldswb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_ldswb_ASI_PNFL,4
	ldswb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! int vis_ldswb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldswb_ASI_PNFL_index,8
	ldswb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! unsigned int vis_lduwb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_lduwb_ASI_PNF,4
	lduwb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! unsigned int vis_lduwb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lduwb_ASI_PNF_index,8
	lduwb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! unsigned int vis_lduwb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_lduwb_ASI_PNFL,4
	lduwb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! unsigned int vis_lduwb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lduwb_ASI_PNFL_index,8
	lduwb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! long vis_ldxb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_ldxb_ASI_PNF,4
	ldxb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! long vis_ldxb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldxb_ASI_PNF_index,8
	ldxb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! long vis_ldxb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_ldxb_ASI_PNFL,4
	ldxb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! long vis_ldxb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldxb_ASI_PNFL_index,8
	ldxb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! long long vis_lddb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_lddb_ASI_PNF,4
	lddb	[%o0]0x82,%o0		! ASI_PNF
	.end
!
! long long vis_lddb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddb_ASI_PNF_index,8
	lddb	[%o0+%o1]0x82,%o0	! ASI_PNF
	.end
!
! long long vis_lddb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_lddb_ASI_PNFL,4
	lddb	[%o0]0x8b,%o0		! ASI_PNFL
	.end
!
! long long vis_lddb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddb_ASI_PNFL_index,8
	lddb	[%o0+%o1]0x8b,%o0	! ASI_PNFL
	.end
!
! flobt vis_ldfb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_ldfb_ASI_PNF,4
	ldb	[%o0]0x82,%f0		! ASI_PNF
	.end
!
! flobt vis_ldfb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldfb_ASI_PNF_index,8
	ldb	[%o0+%o1]0x82,%f0	! ASI_PNF
	.end
!
! flobt vis_ldfb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_ldfb_ASI_PNFL,4
	ldb	[%o0]0x8b,%f0		! ASI_PNFL
	.end
!
! flobt vis_ldfb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_ldfb_ASI_PNFL_index,8
	ldb	[%o0+%o1]0x8b,%f0	! ASI_PNFL
	.end
!
! double vis_lddfb_ASI_PNF(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_PNF,4
	lddb	[%o0]0x82,%f0		! ASI_PNF
	.end
!
! double vis_lddfb_ASI_PNF_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_PNF_index,8
	lddb	[%o0+%o1]0x82,%f0	! ASI_PNF
	.end
!
! double vis_lddfb_ASI_PNFL(void * /*rs1*/);
!
	.inline vis_lddfb_ASI_PNFL,4
	lddb	[%o0]0x8b,%f0		! ASI_PNFL
	.end
!
! double vis_lddfb_ASI_PNFL_index(void * /*rs1*/, long /*index*/);
!
	.inline vis_lddfb_ASI_PNFL_index,8
	lddb	[%o0+%o1]0x8b,%f0	! ASI_PNFL
	.end

!--------------------------------------------------------------------
!
! The following bre the new VIS 2.0 instructions.
!

!
! Edge hbndling instructions which do not set the integer condition codes
!
! int vis_edge8n(void * /*rs1*/, void * /*rs2*/);
!
	.inline vis_edge8n,8
	edge8n	%o0,%o1,%o0
	.end
!
! int vis_edge8ln(void * /*rs1*/, void * /*rs2*/);
!
	.inline vis_edge8ln,8
	edge8ln	%o0,%o1,%o0
	.end
!
! int vis_edge16n(void * /*rs1*/, void * /*rs2*/);
!
	.inline vis_edge16n,8
	edge16n	%o0,%o1,%o0
	.end
!
! int vis_edge16ln(void * /*rs1*/, void * /*rs2*/);
!
	.inline vis_edge16ln,8
	edge16ln	%o0,%o1,%o0
	.end
!
! int vis_edge32n(void * /*rs1*/, void * /*rs2*/);
!
	.inline vis_edge32n,8
	edge32n	%o0,%o1,%o0
	.end
!
! int vis_edge32ln(void * /*rs1*/, void * /*rs2*/);
!
	.inline vis_edge32ln,8
	edge32ln	%o0,%o1,%o0
	.end

!--------------------------------------------------------------------
! Byte mbsk bnd shuffle instructions
!
! void vis_write_bmbsk(unsigned int /*rs1*/, unsigned int /*rs2*/);
!
	.inline vis_write_bmbsk,8
	bmbsk	%o0,%o1,%o0
	.end
!
! double vis_bshuffle(double /*frs1*/, double /*frs2*/);
!
	.inline vis_bshuffle,16
	std	%o0,[%sp+0x48]
	ldd	[%sp+0x48],%f4
	std	%o2,[%sp+0x48]
	ldd	[%sp+0x48],%f10
	bshuffle	%f4,%f10,%f0
	.end

!--------------------------------------------------------------------
! Grbphics stbtus register
!
! unsigned int vis_rebd_bmbsk(void);
!
	.inline vis_rebd_bmbsk,0
	rd	%gsr,%o0
	srlx	%o0,32,%o0
	.end
!
! unsigned long long vis_rebd_gsr64(void);
!
	.inline vis_rebd_gsr64,0
	rd	%gsr,%o1
	srlx	%o1,32,%o0
	.end
!
! void vis_write_gsr64(unsigned long long /* GSR */);
!
	.inline vis_write_gsr64,8
	sllx	%o0,32,%o0
	srl	%o1,0,%o1	! clebr the most significbnt 32 bits of %o1
	or	%o0,%o1,%o3	! join %o0 bnd %o1 into %o3
	wr	%g0,%o3,%gsr
	.end
