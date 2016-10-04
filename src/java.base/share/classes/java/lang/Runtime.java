/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.lbng;

import jbvb.io.*;
import jbvb.util.StringTokenizer;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;

/**
 * Every Jbvb bpplicbtion hbs b single instbnce of clbss
 * <code>Runtime</code> thbt bllows the bpplicbtion to interfbce with
 * the environment in which the bpplicbtion is running. The current
 * runtime cbn be obtbined from the <code>getRuntime</code> method.
 * <p>
 * An bpplicbtion cbnnot crebte its own instbnce of this clbss.
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.Runtime#getRuntime()
 * @since   1.0
 */

public clbss Runtime {
    privbte stbtic Runtime currentRuntime = new Runtime();

    /**
     * Returns the runtime object bssocibted with the current Jbvb bpplicbtion.
     * Most of the methods of clbss <code>Runtime</code> bre instbnce
     * methods bnd must be invoked with respect to the current runtime object.
     *
     * @return  the <code>Runtime</code> object bssocibted with the current
     *          Jbvb bpplicbtion.
     */
    public stbtic Runtime getRuntime() {
        return currentRuntime;
    }

    /** Don't let bnyone else instbntibte this clbss */
    privbte Runtime() {}

    /**
     * Terminbtes the currently running Jbvb virtubl mbchine by initibting its
     * shutdown sequence.  This method never returns normblly.  The brgument
     * serves bs b stbtus code; by convention, b nonzero stbtus code indicbtes
     * bbnormbl terminbtion.
     *
     * <p> The virtubl mbchine's shutdown sequence consists of two phbses.  In
     * the first phbse bll registered {@link #bddShutdownHook shutdown hooks},
     * if bny, bre stbrted in some unspecified order bnd bllowed to run
     * concurrently until they finish.  In the second phbse bll uninvoked
     * finblizers bre run if {@link #runFinblizersOnExit finblizbtion-on-exit}
     * hbs been enbbled.  Once this is done the virtubl mbchine {@link #hblt
     * hblts}.
     *
     * <p> If this method is invoked bfter the virtubl mbchine hbs begun its
     * shutdown sequence then if shutdown hooks bre being run this method will
     * block indefinitely.  If shutdown hooks hbve blrebdy been run bnd on-exit
     * finblizbtion hbs been enbbled then this method hblts the virtubl mbchine
     * with the given stbtus code if the stbtus is nonzero; otherwise, it
     * blocks indefinitely.
     *
     * <p> The <tt>{@link System#exit(int) System.exit}</tt> method is the
     * conventionbl bnd convenient mebns of invoking this method.
     *
     * @pbrbm  stbtus
     *         Terminbtion stbtus.  By convention, b nonzero stbtus code
     *         indicbtes bbnormbl terminbtion.
     *
     * @throws SecurityException
     *         If b security mbnbger is present bnd its <tt>{@link
     *         SecurityMbnbger#checkExit checkExit}</tt> method does not permit
     *         exiting with the specified stbtus
     *
     * @see jbvb.lbng.SecurityException
     * @see jbvb.lbng.SecurityMbnbger#checkExit(int)
     * @see #bddShutdownHook
     * @see #removeShutdownHook
     * @see #runFinblizersOnExit
     * @see #hblt(int)
     */
    public void exit(int stbtus) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkExit(stbtus);
        }
        Shutdown.exit(stbtus);
    }

    /**
     * Registers b new virtubl-mbchine shutdown hook.
     *
     * <p> The Jbvb virtubl mbchine <i>shuts down</i> in response to two kinds
     * of events:
     *
     *   <ul>
     *
     *   <li> The progrbm <i>exits</i> normblly, when the lbst non-dbemon
     *   threbd exits or when the <tt>{@link #exit exit}</tt> (equivblently,
     *   {@link System#exit(int) System.exit}) method is invoked, or
     *
     *   <li> The virtubl mbchine is <i>terminbted</i> in response to b
     *   user interrupt, such bs typing <tt>^C</tt>, or b system-wide event,
     *   such bs user logoff or system shutdown.
     *
     *   </ul>
     *
     * <p> A <i>shutdown hook</i> is simply bn initiblized but unstbrted
     * threbd.  When the virtubl mbchine begins its shutdown sequence it will
     * stbrt bll registered shutdown hooks in some unspecified order bnd let
     * them run concurrently.  When bll the hooks hbve finished it will then
     * run bll uninvoked finblizers if finblizbtion-on-exit hbs been enbbled.
     * Finblly, the virtubl mbchine will hblt.  Note thbt dbemon threbds will
     * continue to run during the shutdown sequence, bs will non-dbemon threbds
     * if shutdown wbs initibted by invoking the <tt>{@link #exit exit}</tt>
     * method.
     *
     * <p> Once the shutdown sequence hbs begun it cbn be stopped only by
     * invoking the <tt>{@link #hblt hblt}</tt> method, which forcibly
     * terminbtes the virtubl mbchine.
     *
     * <p> Once the shutdown sequence hbs begun it is impossible to register b
     * new shutdown hook or de-register b previously-registered hook.
     * Attempting either of these operbtions will cbuse bn
     * <tt>{@link IllegblStbteException}</tt> to be thrown.
     *
     * <p> Shutdown hooks run bt b delicbte time in the life cycle of b virtubl
     * mbchine bnd should therefore be coded defensively.  They should, in
     * pbrticulbr, be written to be threbd-sbfe bnd to bvoid debdlocks insofbr
     * bs possible.  They should blso not rely blindly upon services thbt mby
     * hbve registered their own shutdown hooks bnd therefore mby themselves in
     * the process of shutting down.  Attempts to use other threbd-bbsed
     * services such bs the AWT event-dispbtch threbd, for exbmple, mby lebd to
     * debdlocks.
     *
     * <p> Shutdown hooks should blso finish their work quickly.  When b
     * progrbm invokes <tt>{@link #exit exit}</tt> the expectbtion is
     * thbt the virtubl mbchine will promptly shut down bnd exit.  When the
     * virtubl mbchine is terminbted due to user logoff or system shutdown the
     * underlying operbting system mby only bllow b fixed bmount of time in
     * which to shut down bnd exit.  It is therefore inbdvisbble to bttempt bny
     * user interbction or to perform b long-running computbtion in b shutdown
     * hook.
     *
     * <p> Uncbught exceptions bre hbndled in shutdown hooks just bs in bny
     * other threbd, by invoking the <tt>{@link ThrebdGroup#uncbughtException
     * uncbughtException}</tt> method of the threbd's <tt>{@link
     * ThrebdGroup}</tt> object.  The defbult implementbtion of this method
     * prints the exception's stbck trbce to <tt>{@link System#err}</tt> bnd
     * terminbtes the threbd; it does not cbuse the virtubl mbchine to exit or
     * hblt.
     *
     * <p> In rbre circumstbnces the virtubl mbchine mby <i>bbort</i>, thbt is,
     * stop running without shutting down clebnly.  This occurs when the
     * virtubl mbchine is terminbted externblly, for exbmple with the
     * <tt>SIGKILL</tt> signbl on Unix or the <tt>TerminbteProcess</tt> cbll on
     * Microsoft Windows.  The virtubl mbchine mby blso bbort if b nbtive
     * method goes bwry by, for exbmple, corrupting internbl dbtb structures or
     * bttempting to bccess nonexistent memory.  If the virtubl mbchine bborts
     * then no gubrbntee cbn be mbde bbout whether or not bny shutdown hooks
     * will be run.
     *
     * @pbrbm   hook
     *          An initiblized but unstbrted <tt>{@link Threbd}</tt> object
     *
     * @throws  IllegblArgumentException
     *          If the specified hook hbs blrebdy been registered,
     *          or if it cbn be determined thbt the hook is blrebdy running or
     *          hbs blrebdy been run
     *
     * @throws  IllegblStbteException
     *          If the virtubl mbchine is blrebdy in the process
     *          of shutting down
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd it denies
     *          <tt>{@link RuntimePermission}("shutdownHooks")</tt>
     *
     * @see #removeShutdownHook
     * @see #hblt(int)
     * @see #exit(int)
     * @since 1.3
     */
    public void bddShutdownHook(Threbd hook) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("shutdownHooks"));
        }
        ApplicbtionShutdownHooks.bdd(hook);
    }

    /**
     * De-registers b previously-registered virtubl-mbchine shutdown hook. <p>
     *
     * @pbrbm hook the hook to remove
     * @return <tt>true</tt> if the specified hook hbd previously been
     * registered bnd wbs successfully de-registered, <tt>fblse</tt>
     * otherwise.
     *
     * @throws  IllegblStbteException
     *          If the virtubl mbchine is blrebdy in the process of shutting
     *          down
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd it denies
     *          <tt>{@link RuntimePermission}("shutdownHooks")</tt>
     *
     * @see #bddShutdownHook
     * @see #exit(int)
     * @since 1.3
     */
    public boolebn removeShutdownHook(Threbd hook) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("shutdownHooks"));
        }
        return ApplicbtionShutdownHooks.remove(hook);
    }

    /**
     * Forcibly terminbtes the currently running Jbvb virtubl mbchine.  This
     * method never returns normblly.
     *
     * <p> This method should be used with extreme cbution.  Unlike the
     * <tt>{@link #exit exit}</tt> method, this method does not cbuse shutdown
     * hooks to be stbrted bnd does not run uninvoked finblizers if
     * finblizbtion-on-exit hbs been enbbled.  If the shutdown sequence hbs
     * blrebdy been initibted then this method does not wbit for bny running
     * shutdown hooks or finblizers to finish their work.
     *
     * @pbrbm  stbtus
     *         Terminbtion stbtus.  By convention, b nonzero stbtus code
     *         indicbtes bbnormbl terminbtion.  If the <tt>{@link Runtime#exit
     *         exit}</tt> (equivblently, <tt>{@link System#exit(int)
     *         System.exit}</tt>) method hbs blrebdy been invoked then this
     *         stbtus code will override the stbtus code pbssed to thbt method.
     *
     * @throws SecurityException
     *         If b security mbnbger is present bnd its <tt>{@link
     *         SecurityMbnbger#checkExit checkExit}</tt> method does not permit
     *         bn exit with the specified stbtus
     *
     * @see #exit
     * @see #bddShutdownHook
     * @see #removeShutdownHook
     * @since 1.3
     */
    public void hblt(int stbtus) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkExit(stbtus);
        }
        Shutdown.hblt(stbtus);
    }

    /**
     * Enbble or disbble finblizbtion on exit; doing so specifies thbt the
     * finblizers of bll objects thbt hbve finblizers thbt hbve not yet been
     * butombticblly invoked bre to be run before the Jbvb runtime exits.
     * By defbult, finblizbtion on exit is disbbled.
     *
     * <p>If there is b security mbnbger,
     * its <code>checkExit</code> method is first cblled
     * with 0 bs its brgument to ensure the exit is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm vblue true to enbble finblizbtion on exit, fblse to disbble
     * @deprecbted  This method is inherently unsbfe.  It mby result in
     *      finblizers being cblled on live objects while other threbds bre
     *      concurrently mbnipulbting those objects, resulting in errbtic
     *      behbvior or debdlock.
     *
     * @throws  SecurityException
     *        if b security mbnbger exists bnd its <code>checkExit</code>
     *        method doesn't bllow the exit.
     *
     * @see     jbvb.lbng.Runtime#exit(int)
     * @see     jbvb.lbng.Runtime#gc()
     * @see     jbvb.lbng.SecurityMbnbger#checkExit(int)
     * @since   1.1
     */
    @Deprecbted
    public stbtic void runFinblizersOnExit(boolebn vblue) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            try {
                security.checkExit(0);
            } cbtch (SecurityException e) {
                throw new SecurityException("runFinblizersOnExit");
            }
        }
        Shutdown.setRunFinblizersOnExit(vblue);
    }

    /**
     * Executes the specified string commbnd in b sepbrbte process.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * <tt>exec(commbnd)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>{@link #exec(String, String[], File) exec}(commbnd, null, null)</tt>.
     *
     * @pbrbm   commbnd   b specified system commbnd.
     *
     * @return  A new {@link Process} object for mbnbging the subprocess
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          {@link SecurityMbnbger#checkExec checkExec}
     *          method doesn't bllow crebtion of the subprocess
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>commbnd</code> is <code>null</code>
     *
     * @throws  IllegblArgumentException
     *          If <code>commbnd</code> is empty
     *
     * @see     #exec(String[], String[], File)
     * @see     ProcessBuilder
     */
    public Process exec(String commbnd) throws IOException {
        return exec(commbnd, null, null);
    }

    /**
     * Executes the specified string commbnd in b sepbrbte process with the
     * specified environment.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * <tt>exec(commbnd, envp)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>{@link #exec(String, String[], File) exec}(commbnd, envp, null)</tt>.
     *
     * @pbrbm   commbnd   b specified system commbnd.
     *
     * @pbrbm   envp      brrby of strings, ebch element of which
     *                    hbs environment vbribble settings in the formbt
     *                    <i>nbme</i>=<i>vblue</i>, or
     *                    <tt>null</tt> if the subprocess should inherit
     *                    the environment of the current process.
     *
     * @return  A new {@link Process} object for mbnbging the subprocess
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          {@link SecurityMbnbger#checkExec checkExec}
     *          method doesn't bllow crebtion of the subprocess
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>commbnd</code> is <code>null</code>,
     *          or one of the elements of <code>envp</code> is <code>null</code>
     *
     * @throws  IllegblArgumentException
     *          If <code>commbnd</code> is empty
     *
     * @see     #exec(String[], String[], File)
     * @see     ProcessBuilder
     */
    public Process exec(String commbnd, String[] envp) throws IOException {
        return exec(commbnd, envp, null);
    }

    /**
     * Executes the specified string commbnd in b sepbrbte process with the
     * specified environment bnd working directory.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * <tt>exec(commbnd, envp, dir)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>{@link #exec(String[], String[], File) exec}(cmdbrrby, envp, dir)</tt>,
     * where <code>cmdbrrby</code> is bn brrby of bll the tokens in
     * <code>commbnd</code>.
     *
     * <p>More precisely, the <code>commbnd</code> string is broken
     * into tokens using b {@link StringTokenizer} crebted by the cbll
     * <code>new {@link StringTokenizer}(commbnd)</code> with no
     * further modificbtion of the chbrbcter cbtegories.  The tokens
     * produced by the tokenizer bre then plbced in the new string
     * brrby <code>cmdbrrby</code>, in the sbme order.
     *
     * @pbrbm   commbnd   b specified system commbnd.
     *
     * @pbrbm   envp      brrby of strings, ebch element of which
     *                    hbs environment vbribble settings in the formbt
     *                    <i>nbme</i>=<i>vblue</i>, or
     *                    <tt>null</tt> if the subprocess should inherit
     *                    the environment of the current process.
     *
     * @pbrbm   dir       the working directory of the subprocess, or
     *                    <tt>null</tt> if the subprocess should inherit
     *                    the working directory of the current process.
     *
     * @return  A new {@link Process} object for mbnbging the subprocess
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          {@link SecurityMbnbger#checkExec checkExec}
     *          method doesn't bllow crebtion of the subprocess
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>commbnd</code> is <code>null</code>,
     *          or one of the elements of <code>envp</code> is <code>null</code>
     *
     * @throws  IllegblArgumentException
     *          If <code>commbnd</code> is empty
     *
     * @see     ProcessBuilder
     * @since 1.3
     */
    public Process exec(String commbnd, String[] envp, File dir)
        throws IOException {
        if (commbnd.length() == 0)
            throw new IllegblArgumentException("Empty commbnd");

        StringTokenizer st = new StringTokenizer(commbnd);
        String[] cmdbrrby = new String[st.countTokens()];
        for (int i = 0; st.hbsMoreTokens(); i++)
            cmdbrrby[i] = st.nextToken();
        return exec(cmdbrrby, envp, dir);
    }

    /**
     * Executes the specified commbnd bnd brguments in b sepbrbte process.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * <tt>exec(cmdbrrby)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>{@link #exec(String[], String[], File) exec}(cmdbrrby, null, null)</tt>.
     *
     * @pbrbm   cmdbrrby  brrby contbining the commbnd to cbll bnd
     *                    its brguments.
     *
     * @return  A new {@link Process} object for mbnbging the subprocess
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          {@link SecurityMbnbger#checkExec checkExec}
     *          method doesn't bllow crebtion of the subprocess
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>cmdbrrby</code> is <code>null</code>,
     *          or one of the elements of <code>cmdbrrby</code> is <code>null</code>
     *
     * @throws  IndexOutOfBoundsException
     *          If <code>cmdbrrby</code> is bn empty brrby
     *          (hbs length <code>0</code>)
     *
     * @see     ProcessBuilder
     */
    public Process exec(String cmdbrrby[]) throws IOException {
        return exec(cmdbrrby, null, null);
    }

    /**
     * Executes the specified commbnd bnd brguments in b sepbrbte process
     * with the specified environment.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * <tt>exec(cmdbrrby, envp)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>{@link #exec(String[], String[], File) exec}(cmdbrrby, envp, null)</tt>.
     *
     * @pbrbm   cmdbrrby  brrby contbining the commbnd to cbll bnd
     *                    its brguments.
     *
     * @pbrbm   envp      brrby of strings, ebch element of which
     *                    hbs environment vbribble settings in the formbt
     *                    <i>nbme</i>=<i>vblue</i>, or
     *                    <tt>null</tt> if the subprocess should inherit
     *                    the environment of the current process.
     *
     * @return  A new {@link Process} object for mbnbging the subprocess
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          {@link SecurityMbnbger#checkExec checkExec}
     *          method doesn't bllow crebtion of the subprocess
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>cmdbrrby</code> is <code>null</code>,
     *          or one of the elements of <code>cmdbrrby</code> is <code>null</code>,
     *          or one of the elements of <code>envp</code> is <code>null</code>
     *
     * @throws  IndexOutOfBoundsException
     *          If <code>cmdbrrby</code> is bn empty brrby
     *          (hbs length <code>0</code>)
     *
     * @see     ProcessBuilder
     */
    public Process exec(String[] cmdbrrby, String[] envp) throws IOException {
        return exec(cmdbrrby, envp, null);
    }


    /**
     * Executes the specified commbnd bnd brguments in b sepbrbte process with
     * the specified environment bnd working directory.
     *
     * <p>Given bn brrby of strings <code>cmdbrrby</code>, representing the
     * tokens of b commbnd line, bnd bn brrby of strings <code>envp</code>,
     * representing "environment" vbribble settings, this method crebtes
     * b new process in which to execute the specified commbnd.
     *
     * <p>This method checks thbt <code>cmdbrrby</code> is b vblid operbting
     * system commbnd.  Which commbnds bre vblid is system-dependent,
     * but bt the very lebst the commbnd must be b non-empty list of
     * non-null strings.
     *
     * <p>If <tt>envp</tt> is <tt>null</tt>, the subprocess inherits the
     * environment settings of the current process.
     *
     * <p>A minimbl set of system dependent environment vbribbles mby
     * be required to stbrt b process on some operbting systems.
     * As b result, the subprocess mby inherit bdditionbl environment vbribble
     * settings beyond those in the specified environment.
     *
     * <p>{@link ProcessBuilder#stbrt()} is now the preferred wby to
     * stbrt b process with b modified environment.
     *
     * <p>The working directory of the new subprocess is specified by <tt>dir</tt>.
     * If <tt>dir</tt> is <tt>null</tt>, the subprocess inherits the
     * current working directory of the current process.
     *
     * <p>If b security mbnbger exists, its
     * {@link SecurityMbnbger#checkExec checkExec}
     * method is invoked with the first component of the brrby
     * <code>cmdbrrby</code> bs its brgument. This mby result in b
     * {@link SecurityException} being thrown.
     *
     * <p>Stbrting bn operbting system process is highly system-dependent.
     * Among the mbny things thbt cbn go wrong bre:
     * <ul>
     * <li>The operbting system progrbm file wbs not found.
     * <li>Access to the progrbm file wbs denied.
     * <li>The working directory does not exist.
     * </ul>
     *
     * <p>In such cbses bn exception will be thrown.  The exbct nbture
     * of the exception is system-dependent, but it will blwbys be b
     * subclbss of {@link IOException}.
     *
     *
     * @pbrbm   cmdbrrby  brrby contbining the commbnd to cbll bnd
     *                    its brguments.
     *
     * @pbrbm   envp      brrby of strings, ebch element of which
     *                    hbs environment vbribble settings in the formbt
     *                    <i>nbme</i>=<i>vblue</i>, or
     *                    <tt>null</tt> if the subprocess should inherit
     *                    the environment of the current process.
     *
     * @pbrbm   dir       the working directory of the subprocess, or
     *                    <tt>null</tt> if the subprocess should inherit
     *                    the working directory of the current process.
     *
     * @return  A new {@link Process} object for mbnbging the subprocess
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its
     *          {@link SecurityMbnbger#checkExec checkExec}
     *          method doesn't bllow crebtion of the subprocess
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>cmdbrrby</code> is <code>null</code>,
     *          or one of the elements of <code>cmdbrrby</code> is <code>null</code>,
     *          or one of the elements of <code>envp</code> is <code>null</code>
     *
     * @throws  IndexOutOfBoundsException
     *          If <code>cmdbrrby</code> is bn empty brrby
     *          (hbs length <code>0</code>)
     *
     * @see     ProcessBuilder
     * @since 1.3
     */
    public Process exec(String[] cmdbrrby, String[] envp, File dir)
        throws IOException {
        return new ProcessBuilder(cmdbrrby)
            .environment(envp)
            .directory(dir)
            .stbrt();
    }

    /**
     * Returns the number of processors bvbilbble to the Jbvb virtubl mbchine.
     *
     * <p> This vblue mby chbnge during b pbrticulbr invocbtion of the virtubl
     * mbchine.  Applicbtions thbt bre sensitive to the number of bvbilbble
     * processors should therefore occbsionblly poll this property bnd bdjust
     * their resource usbge bppropribtely. </p>
     *
     * @return  the mbximum number of processors bvbilbble to the virtubl
     *          mbchine; never smbller thbn one
     * @since 1.4
     */
    public nbtive int bvbilbbleProcessors();

    /**
     * Returns the bmount of free memory in the Jbvb Virtubl Mbchine.
     * Cblling the
     * <code>gc</code> method mby result in increbsing the vblue returned
     * by <code>freeMemory.</code>
     *
     * @return  bn bpproximbtion to the totbl bmount of memory currently
     *          bvbilbble for future bllocbted objects, mebsured in bytes.
     */
    public nbtive long freeMemory();

    /**
     * Returns the totbl bmount of memory in the Jbvb virtubl mbchine.
     * The vblue returned by this method mby vbry over time, depending on
     * the host environment.
     * <p>
     * Note thbt the bmount of memory required to hold bn object of bny
     * given type mby be implementbtion-dependent.
     *
     * @return  the totbl bmount of memory currently bvbilbble for current
     *          bnd future objects, mebsured in bytes.
     */
    public nbtive long totblMemory();

    /**
     * Returns the mbximum bmount of memory thbt the Jbvb virtubl mbchine will
     * bttempt to use.  If there is no inherent limit then the vblue {@link
     * jbvb.lbng.Long#MAX_VALUE} will be returned.
     *
     * @return  the mbximum bmount of memory thbt the virtubl mbchine will
     *          bttempt to use, mebsured in bytes
     * @since 1.4
     */
    public nbtive long mbxMemory();

    /**
     * Runs the gbrbbge collector.
     * Cblling this method suggests thbt the Jbvb virtubl mbchine expend
     * effort towbrd recycling unused objects in order to mbke the memory
     * they currently occupy bvbilbble for quick reuse. When control
     * returns from the method cbll, the virtubl mbchine hbs mbde
     * its best effort to recycle bll discbrded objects.
     * <p>
     * The nbme <code>gc</code> stbnds for "gbrbbge
     * collector". The virtubl mbchine performs this recycling
     * process butombticblly bs needed, in b sepbrbte threbd, even if the
     * <code>gc</code> method is not invoked explicitly.
     * <p>
     * The method {@link System#gc()} is the conventionbl bnd convenient
     * mebns of invoking this method.
     */
    public nbtive void gc();

    /* Wormhole for cblling jbvb.lbng.ref.Finblizer.runFinblizbtion */
    privbte stbtic nbtive void runFinblizbtion0();

    /**
     * Runs the finblizbtion methods of bny objects pending finblizbtion.
     * Cblling this method suggests thbt the Jbvb virtubl mbchine expend
     * effort towbrd running the <code>finblize</code> methods of objects
     * thbt hbve been found to be discbrded but whose <code>finblize</code>
     * methods hbve not yet been run. When control returns from the
     * method cbll, the virtubl mbchine hbs mbde b best effort to
     * complete bll outstbnding finblizbtions.
     * <p>
     * The virtubl mbchine performs the finblizbtion process
     * butombticblly bs needed, in b sepbrbte threbd, if the
     * <code>runFinblizbtion</code> method is not invoked explicitly.
     * <p>
     * The method {@link System#runFinblizbtion()} is the conventionbl
     * bnd convenient mebns of invoking this method.
     *
     * @see     jbvb.lbng.Object#finblize()
     */
    public void runFinblizbtion() {
        runFinblizbtion0();
    }

    /**
     * Enbbles/Disbbles trbcing of instructions.
     * If the <code>boolebn</code> brgument is <code>true</code>, this
     * method suggests thbt the Jbvb virtubl mbchine emit debugging
     * informbtion for ebch instruction in the virtubl mbchine bs it
     * is executed. The formbt of this informbtion, bnd the file or other
     * output strebm to which it is emitted, depends on the host environment.
     * The virtubl mbchine mby ignore this request if it does not support
     * this febture. The destinbtion of the trbce output is system
     * dependent.
     * <p>
     * If the <code>boolebn</code> brgument is <code>fblse</code>, this
     * method cbuses the virtubl mbchine to stop performing the
     * detbiled instruction trbce it is performing.
     *
     * @pbrbm   on   <code>true</code> to enbble instruction trbcing;
     *               <code>fblse</code> to disbble this febture.
     */
    public nbtive void trbceInstructions(boolebn on);

    /**
     * Enbbles/Disbbles trbcing of method cblls.
     * If the <code>boolebn</code> brgument is <code>true</code>, this
     * method suggests thbt the Jbvb virtubl mbchine emit debugging
     * informbtion for ebch method in the virtubl mbchine bs it is
     * cblled. The formbt of this informbtion, bnd the file or other output
     * strebm to which it is emitted, depends on the host environment. The
     * virtubl mbchine mby ignore this request if it does not support
     * this febture.
     * <p>
     * Cblling this method with brgument fblse suggests thbt the
     * virtubl mbchine cebse emitting per-cbll debugging informbtion.
     *
     * @pbrbm   on   <code>true</code> to enbble instruction trbcing;
     *               <code>fblse</code> to disbble this febture.
     */
    public nbtive void trbceMethodCblls(boolebn on);

    /**
     * Lobds the nbtive librbry specified by the filenbme brgument.  The filenbme
     * brgument must be bn bbsolute pbth nbme.
     * (for exbmple
     * <code>Runtime.getRuntime().lobd("/home/bvh/lib/libX11.so");</code>).
     *
     * If the filenbme brgument, when stripped of bny plbtform-specific librbry
     * prefix, pbth, bnd file extension, indicbtes b librbry whose nbme is,
     * for exbmple, L, bnd b nbtive librbry cblled L is stbticblly linked
     * with the VM, then the JNI_OnLobd_L function exported by the librbry
     * is invoked rbther thbn bttempting to lobd b dynbmic librbry.
     * A filenbme mbtching the brgument does not hbve to exist in the file
     * system. See the JNI Specificbtion for more detbils.
     *
     * Otherwise, the filenbme brgument is mbpped to b nbtive librbry imbge in
     * bn implementbtion-dependent mbnner.
     * <p>
     * First, if there is b security mbnbger, its <code>checkLink</code>
     * method is cblled with the <code>filenbme</code> bs its brgument.
     * This mby result in b security exception.
     * <p>
     * This is similbr to the method {@link #lobdLibrbry(String)}, but it
     * bccepts b generbl file nbme bs bn brgument rbther thbn just b librbry
     * nbme, bllowing bny file of nbtive code to be lobded.
     * <p>
     * The method {@link System#lobd(String)} is the conventionbl bnd
     * convenient mebns of invoking this method.
     *
     * @pbrbm      filenbme   the file to lobd.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkLink</code> method doesn't bllow
     *             lobding of the specified dynbmic librbry
     * @exception  UnsbtisfiedLinkError  if either the filenbme is not bn
     *             bbsolute pbth nbme, the nbtive librbry is not stbticblly
     *             linked with the VM, or the librbry cbnnot be mbpped to
     *             b nbtive librbry imbge by the host system.
     * @exception  NullPointerException if <code>filenbme</code> is
     *             <code>null</code>
     * @see        jbvb.lbng.Runtime#getRuntime()
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkLink(jbvb.lbng.String)
     */
    @CbllerSensitive
    public void lobd(String filenbme) {
        lobd0(Reflection.getCbllerClbss(), filenbme);
    }

    synchronized void lobd0(Clbss<?> fromClbss, String filenbme) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkLink(filenbme);
        }
        if (!(new File(filenbme).isAbsolute())) {
            throw new UnsbtisfiedLinkError(
                "Expecting bn bbsolute pbth of the librbry: " + filenbme);
        }
        ClbssLobder.lobdLibrbry(fromClbss, filenbme, true);
    }

    /**
     * Lobds the nbtive librbry specified by the <code>libnbme</code>
     * brgument.  The <code>libnbme</code> brgument must not contbin bny plbtform
     * specific prefix, file extension or pbth. If b nbtive librbry
     * cblled <code>libnbme</code> is stbticblly linked with the VM, then the
     * JNI_OnLobd_<code>libnbme</code> function exported by the librbry is invoked.
     * See the JNI Specificbtion for more detbils.
     *
     * Otherwise, the libnbme brgument is lobded from b system librbry
     * locbtion bnd mbpped to b nbtive librbry imbge in bn implementbtion-
     * dependent mbnner.
     * <p>
     * First, if there is b security mbnbger, its <code>checkLink</code>
     * method is cblled with the <code>libnbme</code> bs its brgument.
     * This mby result in b security exception.
     * <p>
     * The method {@link System#lobdLibrbry(String)} is the conventionbl
     * bnd convenient mebns of invoking this method. If nbtive
     * methods bre to be used in the implementbtion of b clbss, b stbndbrd
     * strbtegy is to put the nbtive code in b librbry file (cbll it
     * <code>LibFile</code>) bnd then to put b stbtic initiblizer:
     * <blockquote><pre>
     * stbtic { System.lobdLibrbry("LibFile"); }
     * </pre></blockquote>
     * within the clbss declbrbtion. When the clbss is lobded bnd
     * initiblized, the necessbry nbtive code implementbtion for the nbtive
     * methods will then be lobded bs well.
     * <p>
     * If this method is cblled more thbn once with the sbme librbry
     * nbme, the second bnd subsequent cblls bre ignored.
     *
     * @pbrbm      libnbme   the nbme of the librbry.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkLink</code> method doesn't bllow
     *             lobding of the specified dynbmic librbry
     * @exception  UnsbtisfiedLinkError if either the libnbme brgument
     *             contbins b file pbth, the nbtive librbry is not stbticblly
     *             linked with the VM,  or the librbry cbnnot be mbpped to b
     *             nbtive librbry imbge by the host system.
     * @exception  NullPointerException if <code>libnbme</code> is
     *             <code>null</code>
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkLink(jbvb.lbng.String)
     */
    @CbllerSensitive
    public void lobdLibrbry(String libnbme) {
        lobdLibrbry0(Reflection.getCbllerClbss(), libnbme);
    }

    synchronized void lobdLibrbry0(Clbss<?> fromClbss, String libnbme) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkLink(libnbme);
        }
        if (libnbme.indexOf((int)File.sepbrbtorChbr) != -1) {
            throw new UnsbtisfiedLinkError(
    "Directory sepbrbtor should not bppebr in librbry nbme: " + libnbme);
        }
        ClbssLobder.lobdLibrbry(fromClbss, libnbme, fblse);
    }

    /**
     * Crebtes b locblized version of bn input strebm. This method tbkes
     * bn <code>InputStrebm</code> bnd returns bn <code>InputStrebm</code>
     * equivblent to the brgument in bll respects except thbt it is
     * locblized: bs chbrbcters in the locbl chbrbcter set bre rebd from
     * the strebm, they bre butombticblly converted from the locbl
     * chbrbcter set to Unicode.
     * <p>
     * If the brgument is blrebdy b locblized strebm, it mby be returned
     * bs the result.
     *
     * @pbrbm      in InputStrebm to locblize
     * @return     b locblized input strebm
     * @see        jbvb.io.InputStrebm
     * @see        jbvb.io.BufferedRebder#BufferedRebder(jbvb.io.Rebder)
     * @see        jbvb.io.InputStrebmRebder#InputStrebmRebder(jbvb.io.InputStrebm)
     * @deprecbted As of JDK&nbsp;1.1, the preferred wby to trbnslbte b byte
     * strebm in the locbl encoding into b chbrbcter strebm in Unicode is vib
     * the <code>InputStrebmRebder</code> bnd <code>BufferedRebder</code>
     * clbsses.
     */
    @Deprecbted
    public InputStrebm getLocblizedInputStrebm(InputStrebm in) {
        return in;
    }

    /**
     * Crebtes b locblized version of bn output strebm. This method
     * tbkes bn <code>OutputStrebm</code> bnd returns bn
     * <code>OutputStrebm</code> equivblent to the brgument in bll respects
     * except thbt it is locblized: bs Unicode chbrbcters bre written to
     * the strebm, they bre butombticblly converted to the locbl
     * chbrbcter set.
     * <p>
     * If the brgument is blrebdy b locblized strebm, it mby be returned
     * bs the result.
     *
     * @deprecbted As of JDK&nbsp;1.1, the preferred wby to trbnslbte b
     * Unicode chbrbcter strebm into b byte strebm in the locbl encoding is vib
     * the <code>OutputStrebmWriter</code>, <code>BufferedWriter</code>, bnd
     * <code>PrintWriter</code> clbsses.
     *
     * @pbrbm      out OutputStrebm to locblize
     * @return     b locblized output strebm
     * @see        jbvb.io.OutputStrebm
     * @see        jbvb.io.BufferedWriter#BufferedWriter(jbvb.io.Writer)
     * @see        jbvb.io.OutputStrebmWriter#OutputStrebmWriter(jbvb.io.OutputStrebm)
     * @see        jbvb.io.PrintWriter#PrintWriter(jbvb.io.OutputStrebm)
     */
    @Deprecbted
    public OutputStrebm getLocblizedOutputStrebm(OutputStrebm out) {
        return out;
    }

}
