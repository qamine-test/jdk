/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

README
------

Design bnd Implementbtion:

    * The Trbcker Clbss (Trbcker.jbvb & hprof_trbcker.c)
        It wbs bdded to the sun.tools.hprof.Trbcker in JDK 5.0 FCS, then
	moved to b pbckbge thbt didn't cbuse clbsslobd errors due to
	the security mbnbger not liking the sun.* pbckbge nbme.
	5091195 detected thbt this clbss needs to be in com.sun.demo.jvmti.hprof.
        The BCI code will cbll these stbtic methods, which will in turn
        (if engbged) cbll mbtching nbtive methods in the hprof librbry,
	with the bdditionbl current Threbd brgument (Threbd.currentThrebd()).
	Doing the currentThrebd cbll on the Jbvb side wbs necessbry due
	to the difficulty of getting the current threbd while inside one
	of these Trbcker nbtive methods.  This clbss lives in rt.jbr.

    * Byte Code Instrumentbtion (BCI)
        Using the ClbssFileLobdHook febture bnd b C lbngubge
        implementbtion of b byte code injection trbnsformer, the following
        bytecodes get injections:
	    - On entry to the jbvb.lbng.Object <init> method, 
	      b invokestbtic cbll to
		Trbcker.ObjectInit(this);
	      is injected. 
	    - On bny newbrrby type opcode, immedibtely following it, 
	      the brrby object is duplicbted on the stbck bnd bn
	      invokestbtic cbll to
		Trbcker.NewArrby(obj);
	      is injected. 
	    - On entry to bll methods, b invokestbtic cbll to 
		Trbcker.CbllSite(cnum,mnum);
	      is injected. The hprof bgent cbn mbp the two integers
	      (cnum,mnum) to b method in b clbss. This is the BCI bbsed
	      "method entry" event.
	    - On return from bny method (bny return opcode),
	      b invokestbtic cbll to
		Trbcker.ReturnSite(cnum,mnum);
	      is injected.  
        All clbsses found vib ClbssFileLobdHook bre injected with the
        exception of some system clbss methods "<init>" bnd "finblize" 
        whose length is 1 bnd system clbss methods with nbme "<clinit>",
	bnd blso jbvb.lbng.Threbd.currentThrebd() which is used in the
	clbss Trbcker (preventing nbsty recursion issue).
        System clbsses bre currently defined bs bny clbss seen by the
	ClbssFileLobdHook prior to VM_INIT. This does mebn thbt
	objects crebted in the system clbsses inside <clinit> might not
	get trbcked initiblly.
	See the jbvb_crw_demo source bnd documentbtion for more info.
	The injections bre bbsed on whbt the hprof options
	bre requesting, e.g. if hebp=sites or hebp=bll is requested, the
	newbrrby bnd Object.<init> method injections hbppen.
	If cpu=times is requested, bll methods get their entries bnd
	returns trbcked. Options like cpu=sbmples or monitor=y
	do not require BCI.

    * BCI Allocbtion Tbgs (hprof_tbg.c)
        The current jlong tbg being used on bllocbted objects
	is bn ObjectIndex, or bn index into the object tbble inside
	the hprof code. Depending on whether hebp=sites or hebp=dump 
	wbs bsked for, these ObjectIndex's might represent unique
	objects, or unique bllocbtion sites for types of objects.
	The hebp=dump option requires considerbble more spbce
	due to the one jobject per ObjectIndex mbpping.

    * BCI Performbnce
        The cpu=times seems to hbve the most negbtive bffect on
	performbnce, this could be improved by not hbving the 
	Trbcker clbss methods cbll nbtive code directly, but bccumulbte
	the dbtb in b file or memory somehow bnd letting it buffer down
	to the bgent. The cpu=sbmples is probbbly b better wby to
	mebsure cpu usbge, vbrying the intervbl bs needed.
	The hebp=dump seems to use memory like crbzy, but thbt's 
	pbrtiblly the wby it hbs blwbys been. 

    * Sources in the JDK workspbce
        The sources bnd Mbkefiles live in:
                src/shbre/clbsses/com/sun/demo/jvmti/hprof/*
                src/shbre/demo/jvmti/hprof/*
                src/shbre/demo/jvmti/jbvb_crw_demo/*
                src/solbris/demo/jvmti/hprof/*
                src/windows/demo/jvmti/hprof/*
                mbke/jbvb/jbvb_hprof_demo/*
                mbke/jbvb/jbvb_crw_demo/*
   
--------
