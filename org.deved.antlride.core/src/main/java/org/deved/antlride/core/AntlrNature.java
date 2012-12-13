/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core;

import org.eclipse.dltk.core.ScriptNature;

public class AntlrNature extends ScriptNature {
  public static final String NATURE_ID = AntlrCore.PLUGIN_ID + ".nature";

  /*
  @Override
  public void configure() throws CoreException {
    try {
      addToBuildSpec(DLTKCore.BUILDER_ID);
      Preferences preferenceStore =
          AntlrCorePlugin.getDefault().getPluginPreferences();
      boolean copyJar =
          preferenceStore.getBoolean(AntlrPreferences.COPY_RUNTIME_DEPENDENCY);
      System.out.println(AntlrPreferences.COPY_RUNTIME_DEPENDENCY  + "=" + copyJar);
      String runtimeJar = null;
      if (copyJar) {
        runtimeJar = copyJar("lib");
      }
      final IJavaProject javaProject = JavaCore.create(getProject());
      IClasspathEntry[] classpath = javaProject.getRawClasspath();
      boolean runtimeJarExist = false;
      for (IClasspathEntry classpathEntry : classpath) {
        IPath path = classpathEntry.getPath();
        if (runtimeJar != null
            && classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY
            && path.toString().equals(runtimeJar)) {
          runtimeJarExist = true;
        }
      }
      int entryCount = runtimeJar == null ? 0 : runtimeJarExist ? 0 : + 1;
      this.runtimeJar = null;
      if (entryCount > 0) {
        final IClasspathEntry newEntries[] =
            new IClasspathEntry[classpath.length + entryCount];
        System.arraycopy(classpath, 0, newEntries, 0, classpath.length);
        if (copyJar && !runtimeJarExist) {
          this.runtimeJar = runtimeJar;
          newEntries[newEntries.length - entryCount] =
              JavaCore.newLibraryEntry(new Path(runtimeJar), Path.EMPTY,
                  Path.ROOT);
        }
        IWorkspaceRunnable runnable = new IWorkspaceRunnable() {

          public void run(IProgressMonitor monitor) throws CoreException {
            javaProject.setRawClasspath(newEntries, null);
          }

        };
        JavaCore.run(runnable, null);
      }
    } catch (CoreException e) {
      AntlrCorePlugin.error(e.getMessage(), e);
    }
  }

  private String copyJar(String dir) throws CoreException {
    String runtimeJar;
    IFolder libFolder = getProject().getFolder(dir);
    if (!libFolder.exists()) {
      libFolder.create(true, true, null);
    }
    IPath rawLocation = libFolder.getRawLocation();
    Preferences preferences =
        AntlrCorePlugin.getDefault().getPluginPreferences();
    String antlrRuntimeFile =
        preferences.getString(AntlrPreferences.ANTLR_RUNTIME_FILE);
    System.out.println(AntlrPreferences.ANTLR_RUNTIME_FILE  + "=" +antlrRuntimeFile);
    URL antlrJarUrl =
        AntlrCorePlugin.getDefault().getBundle().getEntry(
            dir + "/" + antlrRuntimeFile);
    BufferedOutputStream bout = null;
    BufferedInputStream bin = null;
    try {
      bout =
          new BufferedOutputStream(new FileOutputStream(new File(rawLocation
              .toFile(), antlrRuntimeFile)));
      bin = new BufferedInputStream(antlrJarUrl.openStream());
      byte array[] = new byte[bin.available()];
      bin.read(array);
      bout.write(array);
    } catch (IOException ioex) {
      ioex.printStackTrace();
    } finally {
      try {
        if (bout != null)
          bout.close();
        if (bin != null)
          bin.close();
      } catch (IOException ioex) {
      }
    }
    libFolder.refreshLocal(IResource.DEPTH_ONE, null);
    StringBuilder builder = new StringBuilder("/");
    builder.append(getProject().getName());
    builder.append("/lib/");
    builder.append(antlrRuntimeFile);
    runtimeJar = builder.toString();
    return runtimeJar;
  }

  @Override
  public void deconfigure() throws CoreException {
    removeFromBuildSpec(DLTKCore.BUILDER_ID);
    final IJavaProject javaProject = JavaCore.create(getProject());
    IClasspathEntry classpath[] = javaProject.getRawClasspath();
    int length = classpath.length;
    if (this.runtimeJar != null) {
      length--;
    }
    final IClasspathEntry newClasspath[] = new IClasspathEntry[length];
    int i = 0;
    for (IClasspathEntry classpathEntry : classpath) {
      IPath path = classpathEntry.getPath();
      if (this.runtimeJar != null) {
        if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY
            && path.toString().equals(runtimeJar)) {
          continue;
        }
      }
      newClasspath[i++] = classpathEntry;
    }
    IWorkspaceRunnable runnable = new IWorkspaceRunnable() {

      public void run(IProgressMonitor monitor) throws CoreException {
        javaProject.setRawClasspath(newClasspath, null);
        IFolder folder = getProject().getFolder("lib");
        Preferences preferences =
            AntlrCorePlugin.getDefault().getPluginPreferences();
        String antlrRuntimeFile =
            preferences.getString(AntlrPreferences.ANTLR_RUNTIME_FILE);
        if (folder.exists()) {
          IFile file = folder.getFile(antlrRuntimeFile);
          if (file.exists()) {
            file.delete(true, null);
          }
          if (folder.members().length == 0) {
            folder.delete(true, null);
          }
        }
      }
    };
    JavaCore.run(runnable, null);
  }*/
}
