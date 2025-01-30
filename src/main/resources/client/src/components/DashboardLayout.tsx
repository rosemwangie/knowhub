import { useState } from "react";
import { Link } from "react-router-dom";
import {
  LayoutDashboard,
  FileText,
  Database,
  LayoutTemplate,
  BarChart,
  Bell,
  Settings,
  ChevronLeft,
  ChevronRight,
} from "lucide-react";
import { cn } from "@/lib/utils";

interface DashboardLayoutProps {
  children: React.ReactNode;
}

const DashboardLayout = ({ children }: DashboardLayoutProps) => {
  const [collapsed, setCollapsed] = useState(false);

  const menuItems = [
    { icon: LayoutDashboard, label: "Dashboard", path: "/dashboard" },
    { icon: FileText, label: "Articles", path: "/articles" },
    { icon: Database, label: "Storage", path: "/storage" },
    { icon: LayoutTemplate, label: "Home Page Builder", path: "/builder" },
    { icon: BarChart, label: "Analytics", path: "/analytics" },
    { icon: Bell, label: "Notifications", path: "/notifications" },
    { icon: Settings, label: "Preferences", path: "/preferences" },
  ];

  return (
    <div className="min-h-screen bg-secondary">
      {/* Sidebar */}
      <div
        className={cn(
          "fixed left-0 top-0 h-full bg-primary text-white transition-all duration-300",
          collapsed ? "w-16" : "w-64"
        )}
      >
        <div className="flex items-center justify-between p-4">
          {!collapsed && <span className="text-xl font-bold">Know Hub</span>}
          <button
            onClick={() => setCollapsed(!collapsed)}
            className="p-2 hover:bg-primary-hover rounded-lg"
          >
            {collapsed ? (
              <ChevronRight className="h-5 w-5" />
            ) : (
              <ChevronLeft className="h-5 w-5" />
            )}
          </button>
        </div>

        <nav className="mt-8">
          {menuItems.map((item) => (
            <Link
              key={item.label}
              to={item.path}
              className="flex items-center px-4 py-3 text-white hover:bg-primary-hover transition-colors"
            >
              <item.icon className="h-5 w-5" />
              {!collapsed && <span className="ml-4">{item.label}</span>}
            </Link>
          ))}
        </nav>
      </div>

      {/* Main content */}
      <div
        className={cn(
          "transition-all duration-300",
          collapsed ? "ml-16" : "ml-64"
        )}
      >
        <div className="p-8">{children}</div>
      </div>
    </div>
  );
};

export default DashboardLayout;